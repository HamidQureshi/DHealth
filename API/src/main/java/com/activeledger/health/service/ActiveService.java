package com.activeledger.health.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.activeledger.java.sdk.generic.transaction.Transaction;
import org.activeledger.java.sdk.generic.transaction.TxObject;
import org.activeledger.java.sdk.key.management.Encryption;
import org.activeledger.java.sdk.signature.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activeledger.health.adapter.ActiveledgerAdapter;
import com.activeledger.health.model.RegisterRequest;
import com.activeledger.health.model.RegisterResponse;
import com.activeledger.health.utility.JsonParsing;


@Component
public class ActiveService {

	@Autowired
	private ActiveledgerAdapter activeledgerAdapter;
	private JsonParsing parsing;
	private Map<String,String> respMap;
	Sign sign;
	
	public ActiveService()
	{
		parsing=new JsonParsing();
		respMap=new HashMap<>();
		
	}
	
	public Map<String, String> register(RegisterRequest register) throws Exception
	{
		
		Encryption encrp=Encryption.valueOf(register.getKeyType().toUpperCase());
		
		respMap=parsing.parseJson( activeledgerAdapter.onboard(encrp,register));
	
		
		return respMap;
	}

	public void createNamespace(String namespace) throws ParseException {
		// TODO Auto-generated method stub
		respMap=parsing.parseJson( activeledgerAdapter.createNamespace(namespace));
	}

	public Map<String, String> sendTransaction(Map<String, Object> reqTransaction) throws ParseException, Exception {
		// TODO Auto-generated method stub
		
		respMap=parsing.parseJson( activeledgerAdapter.sendTransaction(reqTransaction));
		return respMap;
	}

	
	
	
	
	
	
}
