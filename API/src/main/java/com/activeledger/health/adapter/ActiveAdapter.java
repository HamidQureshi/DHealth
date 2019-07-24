package com.activeledger.health.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class ActiveAdapter {
	final static Logger logger = Logger.getLogger(ActiveAdapter.class);

	private AbstractApplicationContext ctx;
	GenericTransaction genericTransaction;
	HttpClient httpclient;
	@Value("${activeledger.sdk.protocol}")
	private String protocol;
	@Value("${activeledger.sdk.ip}")
	private String ip;
	@Value("${activeledger.sdk.port}")
	private String port;
	@Value("${activeledger.api.retrieve.url}")
	private String retrieveUrl;
	@Value("${activeledger.api.search.url}")
	private String searchUrl;
	@Value("${app.doctor}")
	private String doctor;
	@Value("${app.patient}")
	private String patient;

	ObjectMapper mapper;

	public ActiveAdapter() {
		ctx = ActiveledgerJavaSdkApplication.getContext();

		mapper = new ObjectMapper();
		ActiveledgerJavaSdkApplication.setConnection("http", "testnet-uk.activeledger.io", "5260");
		genericTransaction = (GenericTransaction) ctx.getBean("GenericTransaction");
		httpclient = HttpClients.createDefault();
	}

	public JSONObject sendTransaction(Map<String, Object> reqTransaction) throws Exception {
		logger.info("--------Sending transaction to sdk--------");

		Transaction transaction = createTransaction(reqTransaction);
		JSONObject inJson = genericTransaction.transaction(transaction);

		return inJson;

	}

	private Transaction createTransaction(Map<String, Object> reqTransaction) throws JsonProcessingException {
		Transaction transaction = new Transaction();
		TxObject txObject = new TxObject();
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

		HttpGet httpGet = new HttpGet(retrieveUrl + "/" + identity);
		HttpResponse response = httpclient.execute(httpGet);

		String responseAsString = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(responseAsString);

		return obj;
	}

	public JSONObject getUsers(String type) throws Exception {

		String doctorSql = "SELECT * FROM X WHERE type=" + doctor;
		String patientSql = "SELECT * FROM X WHERE type=" + patient;
		URIBuilder builder = new URIBuilder(searchUrl);

		if (type.equalsIgnoreCase("patients"))
			builder.setParameter("sql", patientSql);
		else if (type.equalsIgnoreCase("doctors"))
			builder.setParameter("sql", doctorSql);

		HttpGet httpGet = new HttpGet(builder.build());
		HttpResponse response = httpclient.execute(httpGet);
		String responseAsString = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(responseAsString);

		return obj;
	}

	public JSONObject getAssignedpatients(String identity) throws Exception {

		Map<String, Object> sql = getPatientsQuery(identity);
		System.out.println("sql------"+sql);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
		String json = gson.toJson(sql);
		// searchUrl
		URIBuilder builder = new URIBuilder(searchUrl);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(builder.build());

		StringEntity entity = new StringEntity(json);
		entity.setContentType("application/json");
		httppost.setEntity(entity);
	
		HttpResponse response = httpclient.execute(httppost);
		String responseAsString = EntityUtils.toString(response.getEntity());

		// TODO: Should be done in a better way with better checks.

		JSONObject obj = new JSONObject(responseAsString);

		JSONArray reps = obj.getJSONArray("streams");
		List<String> patients = new ArrayList<>();

		Iterator itr = reps.iterator();
		while (itr.hasNext()) {
			JSONObject id = (JSONObject) itr.next();
			patients.add(id.getString("patientId"));
		}

		builder = new URIBuilder(retrieveUrl);

		httppost = new HttpPost(builder.build());
		List<String> patientsList = patients.stream().distinct().collect(Collectors.toList());

		String pId = gson.toJson(patientsList);
		entity = new StringEntity(pId);
		entity.setContentType("application/json");
		httppost.setEntity(entity);
		response = httpclient.execute(httppost);

		responseAsString = EntityUtils.toString(response.getEntity());

		JSONObject jlist = new JSONObject(responseAsString);

		return jlist;
	}

	private Map<String, Object> getPatientsQuery(String identity) {
		Map<String, Object> sql = new HashMap<>();
		Map<String, Object> selector = new HashMap<>();
		// JSONObject _id = new JSONObject();

		List<String> fieldsList = new ArrayList<>();
		List<String> doctorList = new ArrayList<>();

		Map<String, Object> _id = new HashMap<>();
		_id.put("$gt", null);
		selector.put("_id", _id);

		doctorList.add(identity);
		Map<String, Object> reportDoctors = new HashMap<>();
		reportDoctors.put("$in", doctorList);
		selector.put("report.doctors", reportDoctors);

		Map<String, Object> mango = new HashMap<>();
		mango.put("selector", selector);
		fieldsList.add("patientId");
		mango.put("fields", fieldsList);

		sql.put("sql", "string");
		sql.put("mango", mango);
		return sql;
	}

	public JSONObject getReports(String identity) throws Exception {

		String sql = "SELECT reports FROM X WHERE _id=" + identity;
		URIBuilder builder = new URIBuilder(searchUrl);
		builder.setParameter("sql", sql);

		HttpGet httpGet = new HttpGet(builder.build());
		HttpResponse response = httpclient.execute(httpGet);

		String responseAsString = EntityUtils.toString(response.getEntity());
		JSONObject obj = new JSONObject(responseAsString);
		return obj;
	}

}
