package com.activeledger.health.adapter;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import org.activeledger.java.sdk.activeledgerjavasdk.ActiveledgerJavaSdkApplication;
import org.activeledger.java.sdk.generic.transaction.GenericTransaction;
import org.activeledger.java.sdk.generic.transaction.Transaction;
import org.activeledger.java.sdk.generic.transaction.TxObject;
import org.activeledger.java.sdk.key.management.Encryption;
import org.activeledger.java.sdk.key.management.KeyGen;
import org.activeledger.java.sdk.signature.Sign;
import org.activeledger.java.sdk.utility.Utility;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.activeledger.health.dao.ActiveDao;
import com.activeledger.health.model.RegisterRequest;
import com.activeledger.health.model.User;
import com.activeledger.health.utility.JsonParsing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class ActiveledgerAdapter {
	
	
	@Autowired
	ActiveDao activeDao;
	
	private AbstractApplicationContext ctx;
	Sign sign;
	private ObjectMapper mapper;
	GenericTransaction genericTransaction;

	private JsonParsing parsing;
	
	public ActiveledgerAdapter()
	{
		ctx = ActiveledgerJavaSdkApplication.getContext();
		ActiveledgerJavaSdkApplication.setConnection("http", "testnet-uk.activeledger.io", "5260");
		sign = (Sign) ctx.getBean("Sign");
		genericTransaction = (GenericTransaction) ctx.getBean("GenericTransaction");
		mapper = new ObjectMapper();
		parsing=new JsonParsing();
		
		
	}

// Generate RSA keyPair
	public KeyPair generateRSAkeyPair()
	
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, IOException {
		 KeyGen keyGen= (KeyGen) ctx.getBean("KeyGen");
		return keyGen.generateKeyPair(Encryption.RSA);
	}
	
	// Generate ECDSA KeyPair
	public KeyPair generateECDSAkeyPair()
				throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, IOException {
		KeyGen keyGen= (KeyGen) ctx.getBean("KeyGen");
		return	keyGen.generateKeyPair(Encryption.EC);
			 
		}
	public JSONObject onboard(Encryption encrp, RegisterRequest register) throws Exception {
		User user=new User();
		KeyPair keyPair=null;
		if(encrp==Encryption.RSA)
			keyPair = generateRSAkeyPair();
		if(encrp==Encryption.EC)
			keyPair =generateECDSAkeyPair();
		String privKey=Utility.convertToStringPemFormat("priv-key.pem","PRIVATE KEY",keyPair.getPrivate());//store in db
		String pubKey=Utility.convertToStringPemFormat("pub-key.pem","PUBLIC KEY",keyPair.getPublic());//store in db
		Transaction transaction=createOnboardRequest(encrp,register,pubKey,keyPair);
		 JSONObject inJson = genericTransaction.transaction(transaction);
		 String identity =parsing.parseJson(inJson).get("id");//store in db
		 user.setAge(register.getAge());
		 user.setName(register.getName());
		 
		 user.setIdentity(identity);
		 activeDao.saveUser(user);
		 
		
		
		return inJson;
	}

	public Transaction createOnboardRequest(Encryption encrp, RegisterRequest register, String pubKey, KeyPair privKey) throws JsonProcessingException
	{
		
		Map<String,Object> input=new HashMap<>();
		Map<String,Object> input1=new HashMap<>();
		Map<String,Object> signature=new HashMap<>();
		Transaction transaction = new Transaction();
		TxObject txObject = new TxObject();
		
	
		txObject.setContract("onboard");
		//txObject.setEntry(register.get("Entry").toString());
		txObject.setNamespace("default");//register.getName()
		input1.put("publicKey", pubKey);
		input1.put("type", register.getKeyType().toLowerCase());
		input1.put("name",register.getName());
		input1.put("age",register.getAge());
		input1.put("sex",register.getSex());
		
		input.put(register.getKeyName(), input1);
		
		txObject.setInputIdentity(input);
		transaction.setTxObject(txObject);
		String signed = sign.signMessage(mapper.writeValueAsBytes(txObject), privKey, encrp);
		signature.put(register.getKeyName(), signed);
		transaction.setSignature(signature);
		transaction.setSelfSign(true);
		//System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transaction));
		return transaction;
		
	}

	public JSONObject createNamespace(String namespace) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createNamespaceRequest()
	{
		/*input=new HashMap<>();
		input1=new HashMap<>();
		signature=new HashMap<>();
		transaction = new Transaction();
		txObject = new TxObject();
		
		
		txObject.setContract("namespace");
		//txObject.setEntry(register.get("Entry").toString());
		txObject.setNamespace("default");//register.getName()
		input1.put("publicKey", pubKey);
		input1.put("type", register.getKeyType().toLowerCase());
		input1.put("name",register.getName());
		input1.put("age",register.getAge());
		input1.put("sex",register.getSex());
		
		input.put(register.getKeyName(), input1);
		
		txObject.setInputIdentity(input);
		transaction.setTxObject(txObject);
		String signed = sign.signMessage(mapper.writeValueAsBytes(txObject), privKey, encrp);
		signature.put(register.getKeyName(), signed);
		transaction.setSignature(signature);
		transaction.setSelfSign(true);*/
	}

	public JSONObject sendTransaction(Map<String, Object> reqTransaction) throws Exception {
		Transaction transaction=createTransaction(reqTransaction);
		
		 JSONObject inJson = genericTransaction.transaction(transaction);
		 System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transaction));
		 return inJson;
		
		
	}

	private Transaction createTransaction(Map<String, Object> reqTransaction) {
		Transaction transaction=new Transaction();
		TxObject txObject = new TxObject();
		System.out.println("in contract:::"+reqTransaction);
		if(reqTransaction.get("$tx")!=null)
		{
			Map<String,Object> txMap=(Map) reqTransaction.get("$tx");

			if(txMap.get("$contract")!=null)
				txObject.setContract(txMap.get("$contract").toString());
		if(txMap.get("$entry")!=null)
		txObject.setEntry(txMap.get("$entry").toString());
		if(txMap.get("$i")!=null)
		txObject.setInputIdentity((Map)txMap.get("$i"));
		if(txMap.get("$namespace")!=null)
		txObject.setNamespace(txMap.get("$namespace").toString());
		if(txMap.get("$o")!=null)
		txObject.setOutputIdentity((Map)txMap.get("$o"));
		if(txMap.get("$r")!=null)
		txObject.setStreamState((Map)txMap.get("$r"));
		}
		transaction.setTxObject(txObject);
		if(reqTransaction.get("$territoriality")!=null)
			transaction.setTerritoriality(reqTransaction.get("$territoriality").toString());
		if(reqTransaction.get("$selfsign")!=null)
		transaction.setSelfSign((Boolean)reqTransaction.get("$selfsign"));
		if(reqTransaction.get("$sigs")!=null)
		transaction.setSignature((Map)reqTransaction.get("$sigs"));
		
		
		
		
		return transaction;
	}
	
	
}
