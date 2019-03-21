package com.activeledger.health.adapter;

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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.activeledger.health.dao.ActiveDao;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ActiveledgerAdapter {

	@Autowired
	ActiveDao activeDao;

	private AbstractApplicationContext ctx;

	private ObjectMapper mapper;
	GenericTransaction genericTransaction;
	HttpClient httpclient;

	public ActiveledgerAdapter() {
		ctx = ActiveledgerJavaSdkApplication.getContext();
		ActiveledgerJavaSdkApplication.setConnection("http", "testnet-uk.activeledger.io", "5260");

		genericTransaction = (GenericTransaction) ctx.getBean("GenericTransaction");
		mapper = new ObjectMapper();

		httpclient = HttpClients.createDefault();

	}

	public JSONObject sendTransaction(Map<String, Object> reqTransaction) throws Exception {
		Transaction transaction = createTransaction(reqTransaction);

		JSONObject inJson = genericTransaction.transaction(transaction);
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transaction));
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

	public String retrieveUser(String identity) throws Exception {
		// TODO Auto-generated method stub
		HttpGet httpGet = new HttpGet("http://testnet-uk.activeledger.io:5261/api/stream/" + identity);
		System.out.println(httpGet.getURI());

		HttpResponse response = httpclient.execute(httpGet);

		String responseAsString = EntityUtils.toString(response.getEntity());
		return responseAsString;

	}

}
