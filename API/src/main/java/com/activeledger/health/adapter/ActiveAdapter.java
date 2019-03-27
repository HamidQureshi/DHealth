package com.activeledger.health.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.activeledger.java.sdk.activeledgerjavasdk.ActiveledgerJavaSdkApplication;
import org.activeledger.java.sdk.generic.transaction.GenericTransaction;
import org.activeledger.java.sdk.generic.transaction.Transaction;
import org.activeledger.java.sdk.generic.transaction.TxObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ActiveAdapter {
	final static Logger logger = Logger.getLogger(ActiveAdapter.class);

	private AbstractApplicationContext ctx;
	GenericTransaction genericTransaction;
	HttpClient httpclient;
	@Value("${active.ledger.sdk.url}")
	private String sdkLedgerUrl;
	@Value("${active.ledger.retrieve.user.url}")
	private String retrieveUserUrl;

	public ActiveAdapter() {
		ctx = ActiveledgerJavaSdkApplication.getContext();
		//String []url=sdkLedgerUrl.split(":");//protocol:url:port
		System.out.println("url---"+sdkLedgerUrl);
		ActiveledgerJavaSdkApplication.setConnection("http","testnet-uk.activeledger.io","5260");

		genericTransaction = (GenericTransaction) ctx.getBean("GenericTransaction");
		httpclient = HttpClients.createDefault();

	}

	public JSONObject sendTransaction(Map<String, Object> reqTransaction) throws Exception {
		logger.info("--------Sending transaction to sdk--------");
	
		Transaction transaction = createTransaction(reqTransaction);
		JSONObject inJson = genericTransaction.transaction(transaction);


		return inJson;

	}

	private Transaction createTransaction(Map<String, Object> reqTransaction) {
		Transaction transaction = new Transaction();
		TxObject txObject = new TxObject();
		System.out.println("in contract:::" + reqTransaction);
		if (reqTransaction.get("$tx") != null) {
			Map<String, Object> txMap = (Map) reqTransaction.get("$tx");

			if (txMap.get("$contract") != null)
				txObject.setContract(txMap.get("$contract").toString());
			if (txMap.get("$entry") != null)
				txObject.setEntry(txMap.get("$entry").toString());
			if (txMap.get("$i") != null)
				txObject.setInputIdentity((Map) txMap.get("$i"));
			if (txMap.get("$namespace") != null)
				txObject.setNamespace(txMap.get("$namespace").toString());
			if (txMap.get("$o") != null)
				txObject.setOutputIdentity((Map) txMap.get("$o"));
			if (txMap.get("$r") != null)
				txObject.setStreamState((Map) txMap.get("$r"));
		}
		transaction.setTxObject(txObject);
		if (reqTransaction.get("$territoriality") != null)
			transaction.setTerritoriality(reqTransaction.get("$territoriality").toString());
		if (reqTransaction.get("$selfsign") != null)
			transaction.setSelfSign((Boolean) reqTransaction.get("$selfsign"));
		if (reqTransaction.get("$sigs") != null)
			transaction.setSignature((Map) reqTransaction.get("$sigs"));

		return transaction;
	}

	public JSONObject retrieveUser(String identity) throws Exception {
		
		HttpGet httpGet = new HttpGet(retrieveUserUrl + identity);

		HttpResponse response = httpclient.execute(httpGet);
		
		String responseAsString = EntityUtils.toString(response.getEntity());
		JSONObject obj=new JSONObject(responseAsString);

		Map<String,String> userDetails=new HashMap<>();
		
		return obj;

	}

}
