package com.example.hamid.dhealth;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.model.Territoriality;
import com.example.activeledgersdk.utility.KeyType;
import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.Preference.PreferenceManager;
import com.example.hamid.dhealth.utils.Configurations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyPair;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.activeledgersdk.ActiveLedgerSDK.signMessage;

public class ActiveLedgerHelper {


    static ActiveLedgerHelper instance = null;
    @Inject
    PreferenceManager preferenceManager;
    private Disposable disposable;
    private KeyPair key_Pair = null;
    private KeyType keyType = KeyType.RSA;
    private MutableLiveData<String> publickey = new MutableLiveData<>();
    private MutableLiveData<String> privatekey = new MutableLiveData<>();
    private String keyname = "activeledgerkey";
    private MutableLiveData<String> onBoardId;
    private MutableLiveData<String> onBoardName;
    private MutableLiveData<String> showToast = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginApp = new MutableLiveData<>();
    private Context context = null;

    public static ActiveLedgerHelper getInstance() {

        synchronized (ActiveLedgerHelper.class) {
            if (instance == null) {
                instance = new ActiveLedgerHelper();
            }
        }
        return instance;
    }


    public void setupALSDK(Context context) {
        ActiveLedgerSDK.getInstance().initSDK(context, "http", "testnet-uk.activeledger.io", "5260");
        this.context = context;
    }


    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public KeyPair getKey_Pair() {
        return key_Pair;
    }

    public void setKey_Pair(KeyPair key_Pair) {
        this.key_Pair = key_Pair;
    }

    public void activityOnDestroy() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }


    public MutableLiveData<String> getPublickey() {
        if (publickey == null) {
            publickey = new MutableLiveData<>();
        }
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey.postValue(publickey);
    }

    public MutableLiveData<String> getPrivatekey() {
        if (privatekey == null) {
            privatekey = new MutableLiveData<>();
        }
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey.postValue(privatekey);
    }


    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public MutableLiveData<String> getOnBoardId() {
        if (onBoardId == null) {
            onBoardId = new MutableLiveData<>();
        }
        return onBoardId;
    }

    public void setOnBoardId(String onBoardId) {
        this.onBoardId.postValue(onBoardId);
    }

    public MutableLiveData<String> getOnBoardName() {
        if (onBoardName == null) {
            onBoardName = new MutableLiveData<>();
        }
        return onBoardName;
    }

    public void setOnBoardName(String onBoardName) {
        this.onBoardName.postValue(onBoardName);
    }

    public MutableLiveData<String> getShowToast() {
        if (showToast == null) {
            showToast = new MutableLiveData<>();
        }
        return showToast;
    }

    public void setShowToast(String message) {
        this.showToast.postValue(message);
    }

    public void loginApp(Boolean message) {
        this.loginApp.postValue(message);
    }

    public void getTerritorialityList() {
        ActiveLedgerSDK.getInstance().getTerritorialityStatus()
                .subscribe(new Observer<Territoriality>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Territoriality response) {
                        //Territoriality object has a list of neighbours and reference to left and right node
                        Log.e("Territoriality --->", response.getStatus());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void getTransactionData(String id) {
        ActiveLedgerSDK.getInstance().getTransactionData(id)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String response) {

                        Log.e("transaction data --->", response);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    // this method return the onboard transaction as JSON object
    public JSONObject onboardTransaction(KeyPair keyPair, KeyType type, String first_name, String last_name, String email,
                                         String date_of_birth, String phone_number, String address, String security, String profile_type, String gender, String dp) {

        JSONObject transaction = new JSONObject();
        JSONObject $sigs = new JSONObject();
        JSONObject identity = new JSONObject();
        JSONObject $i = new JSONObject();
        JSONObject $tx = new JSONObject();

        try {

            String pubKey = null;
            try {
                pubKey = Utility.readFileAsString(Utility.getPublicKeyFileName(email));
                System.out.println("public:::" + pubKey.toString());
                String priKey = Utility.readFileAsString(Utility.getPrivateKeyFileName(email));
                System.out.println("public:::" + priKey.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            $tx.put("$contract", Configurations.contract);
            $tx.put("$namespace", Configurations.namespace);
            $tx.put("$entry", "create");

            identity.put("publicKey", pubKey);

            if (type == KeyType.RSA)
                identity.put("type", "rsa");
            else if (type == KeyType.EC)
                identity.put("type", "secp256k1");

            identity.put("first_name", first_name);
            identity.put("last_name", last_name);
            identity.put("email", email);
            identity.put("date_of_birth", date_of_birth);
            identity.put("phone_number", phone_number);
            identity.put("address", address);
            identity.put("security", security);
            identity.put("profile_type", profile_type);
            identity.put("gender", gender);
            identity.put("dp", dp);


            $i.put(ActiveLedgerSDK.KEYNAME, identity);
            $tx.put("$i", $i);

            try {

                String signTransactionObject = Utility.getInstance().convertJSONObjectToString($tx);
                $sigs.put(ActiveLedgerSDK.KEYNAME, signMessage(signTransactionObject.getBytes(), keyPair, type, email));

            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to sign object:" + e.getMessage());
            }


            transaction.put("$tx", $tx);
            transaction.put("$selfsign", true);
            transaction.put("$sigs", $sigs);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public JSONObject createUpdateUserTransaction(KeyPair keyPair, KeyType type, String first_name, String last_name,
                                                  String date_of_birth, String phone_number, String address, String dp, String email) {

        JSONObject transaction = new JSONObject();
        JSONObject $sigs = new JSONObject();
        JSONObject identity = new JSONObject();
        JSONObject $i = new JSONObject();
        JSONObject $o = new JSONObject();
        JSONObject $tx = new JSONObject();
        if(preferenceManager == null){
            preferenceManager = new PreferenceManager();
        }
        String onboard_id = preferenceManager.readFromPref(context, PreferenceKeys.SP_IDENTITY, "null");

        try {

//            String pubKey = null;
//            try {
//                pubKey = Utility.readFileAsString(Utility.PUBLICKEY_FILE);
//                Log.e("public:::", pubKey.toString());
//                String priKey = Utility.readFileAsString(Utility.PRIVATEKEY_FILE);
//                Log.e("private:::" , priKey.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            $tx.put("$contract", Configurations.contract);
            $tx.put("$namespace", Configurations.namespace);
            $tx.put("$entry", "update");

//            identity.put("publicKey", pubKey);

//            if (type == KeyType.RSA)
//                identity.put("type", "rsa");
//            else if (type == KeyType.EC)
//                identity.put("type", "secp256k1");

            $i.put(onboard_id, identity);

            JSONObject updatedObject = new JSONObject();
            updatedObject.put("first_name", first_name);
            updatedObject.put("last_name", last_name);
            updatedObject.put("date_of_birth", date_of_birth);
            updatedObject.put("phone_number", phone_number);
            updatedObject.put("address", address);
            updatedObject.put("dp", dp);

            $o.put(onboard_id, updatedObject);

            $tx.put("$i", $i);
            $tx.put("$o", $o);

            try {

                String signTransactionObject = Utility.getInstance().convertJSONObjectToString($tx);
                $sigs.put(onboard_id, signMessage(signTransactionObject.getBytes(), keyPair, type, email));

            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to sign object:" + e.getMessage());
            }


            transaction.put("$tx", $tx);
            transaction.put("$selfsign", false);
            transaction.put("$sigs", $sigs);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public JSONObject createUploadReportTransaction(KeyPair keyPair, KeyType type, String name, String title, String status, String uploaddate, String assignedto,
                                                    String signeddate, String description, String base64document, String fileName, ArrayList<String> selected_array, String email) {

        JSONObject transaction = new JSONObject();
        JSONObject $sigs = new JSONObject();
        JSONObject identity = new JSONObject();
        JSONObject $i = new JSONObject();
        JSONObject $o = new JSONObject();
        JSONObject $tx = new JSONObject();
        if(preferenceManager == null){
            preferenceManager = new PreferenceManager();
        }
        String onboard_id = preferenceManager.readFromPref(context, PreferenceKeys.SP_IDENTITY, "null");

        try {

            $tx.put("$contract", Configurations.report_contract_lbl);
            $tx.put("$namespace", Configurations.namespace);
            $tx.put("$entry", "upload");

            $i.put(onboard_id, identity);

            JSONArray reports_array = new JSONArray();
            JSONObject report = new JSONObject();

            report.put("patientName", name);
            report.put("title", title);
            report.put("doctors", new JSONArray(selected_array));
            report.put("description", description);
            report.put("fileName", fileName);
            report.put("content", base64document);
            reports_array.put(report);
            $o.put(onboard_id, reports_array);

            $tx.put("$i", $i);
            $tx.put("$o", $o);

            try {

                String signTransactionObject = Utility.getInstance().convertJSONObjectToString($tx);
                $sigs.put(onboard_id, signMessage(signTransactionObject.getBytes(), keyPair, type, email));

            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to sign object:" + e.getMessage());
            }

            transaction.put("$tx", $tx);
            transaction.put("$selfsign", false);
            transaction.put("$sigs", $sigs);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public JSONObject createUpdateReportTransaction(KeyPair keyPair, KeyType type, String name, String title, String status, String uploaddate, String assignedto,
                                                    String signeddate, String description, String base64document, String fileName, ArrayList<String> selected_array, String email) {

        JSONObject transaction = new JSONObject();
        JSONObject $sigs = new JSONObject();
        JSONObject identity = new JSONObject();
        JSONObject $i = new JSONObject();
        JSONObject $o = new JSONObject();
        JSONObject $tx = new JSONObject();
        if(preferenceManager == null){
            preferenceManager = new PreferenceManager();
        }
        String onboard_id = preferenceManager.readFromPref(context, PreferenceKeys.SP_IDENTITY, "null");

        try {

            $tx.put("$contract", Configurations.report_contract_lbl);
            $tx.put("$namespace", Configurations.namespace);
            $tx.put("$entry", "update");

            $i.put(onboard_id, identity);

            JSONArray reports_array = new JSONArray();
            JSONObject report = new JSONObject();

//            report.put("patientName", name);
//            report.put("title", title);
            report.put("doctors", new JSONArray(selected_array));
//            report.put("description", description);
//            report.put("fileName", fileName);
            report.put("content", base64document);
            reports_array.put(report);
            $o.put(onboard_id, reports_array);

            $tx.put("$i", $i);
            $tx.put("$o", $o);

            try {

                String signTransactionObject = Utility.getInstance().convertJSONObjectToString($tx);
                $sigs.put(onboard_id, signMessage(signTransactionObject.getBytes(), keyPair, type, email));

            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to sign object:" + e.getMessage());
            }


            transaction.put("$tx", $tx);
            transaction.put("$selfsign", false);
            transaction.put("$sigs", $sigs);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transaction;
    }


}
