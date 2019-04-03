package com.activeledger.health.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activeledger.java.sdk.activeledgerjavasdk.ActiveledgerJavaSdkApplication;
import org.activeledger.java.sdk.generic.transaction.GenericTransaction;
import org.activeledger.java.sdk.generic.transaction.Transaction;
import org.activeledger.java.sdk.generic.transaction.TxObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

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

	public JSONObject getUsers(String type) throws Exception{
		
		String doctorSql="SELECT * FROM X WHERE type='healthtestnet.Doctor'";
		String patientSql="SELECT * FROM X WHERE type='healthtestnet.Patient'";
		URIBuilder builder=builder = new URIBuilder("http://testnet-uk.activeledger.io:5261/api/stream/search");
		
		if(type.equalsIgnoreCase("patients"))
			builder.setParameter("sql", patientSql);
		else if(type.equalsIgnoreCase("doctors"))
			builder.setParameter("sql", doctorSql);
		
			
		
		HttpGet httpGet = new HttpGet(builder.build());
		//String mongo="db.x.find( { type: \"dhealth.activeledger.identity.Doctor\" } ) LIMIT 5";
		HttpResponse response = httpclient.execute(httpGet);
		//StringEntity entity = new StringEntity(mongo);
		//entity.setContentType("application/json");
		//httpPost.setEntity(entity);
		
		//HttpResponse response = httpclient.execute(httpPost);
		
		
		String responseAsString = EntityUtils.toString(response.getEntity());
 

		JSONObject obj=new JSONObject(responseAsString);

		return obj;
	}

	public JSONObject getAssignedpatients(String identity) throws Exception {
		System.out.print("----------hello1");
		JSONObject sql=new  JSONObject();
		BasicDBObject inQuery3 = new BasicDBObject();
		BasicDBObject inQuery2 = new BasicDBObject();
		BasicDBObject inQuery1 = new BasicDBObject();
		BasicDBObject inQuery = new BasicDBObject();
		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		list2.add("_id");
		list2.add("first_name");
		list2.add("profile_type");
		list2.add("reports");
		list.add("402afc3bcf4a43384bcaf14ba0cafd3812af94aba71a4e62b9bf60f2b704be49");
		inQuery.put("doctors", new BasicDBObject("$in", list));
		inQuery1.put("$elemMatch", inQuery);
		inQuery2.put("reports", inQuery1);
		inQuery3.put("selector", inQuery2);
		inQuery3.put("fields", list2);
		sql.put("sql","string");
		sql.put("mango",inQuery3);
		System.out.println("Query:"+sql.toString());

		URIBuilder builder=builder = new URIBuilder("http://testnet-uk.activeledger.io:5261/api/stream/search");
		
	
			
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(builder.build());
		
		StringEntity entity = new StringEntity(sql.toString());
		entity.setContentType("application/json");
		httppost.setEntity(entity);
		HttpResponse response = httpclient.execute(httppost);

		String responseAsString = EntityUtils.toString(response.getEntity());

		JSONObject obj=new JSONObject(responseAsString);
		JSONArray reps=obj.getJSONArray("streams").getJSONObject(0).getJSONArray("reports");
		boolean assigned=false;
			//JSONObject rr=(JSONObject)r;
			Iterator itr = reps.iterator();
	        while (itr.hasNext()) {
	            JSONObject obj1 = (JSONObject) itr.next();
	            Iterator itr1 = obj1.getJSONArray("doctors").iterator();
	            while (itr1.hasNext()) 
	            { String id=(String) itr1.next();
	            	if(id.equals("402afc3bcf4a43384bcaf14ba0cafd3812af94aba71a4e62b9bf60f2b704be49"))
	            	{
	            		assigned=true;
	            		break;
	            	}
	            }
	            if(!assigned)
	            {
	            	itr.remove();
	            }
	            
	        }
	
		obj.remove("warning");

		return obj;
	}

}
