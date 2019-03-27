package com.example.hamid.dhealth;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.model.Territoriality;
import com.example.activeledgersdk.utility.KeyType;
import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.Activities.ProfileScreen;
import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyPair;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.example.activeledgersdk.ActiveLedgerSDK.signMessage;

public class ActiveLedgerHelper {


    static ActiveLedgerHelper instance = null;
    ProfileScreen profileScreen = null;
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


    public void generatekeys(ProfileScreen activity, String first_name, String last_name, String email,
                             String date_of_birth, String phone_number, String address, String security, String profile_type, String gender, String dp) {

        profileScreen = activity;

        ActiveLedgerSDK.getInstance().generateAndSetKeyPair(keyType, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeyPair>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.d("MainActivity", "onComplete");

                        try {
                            setPublickey(ActiveLedgerSDK.readFileAsString((Utility.PUBLICKEY_FILE)));
                            setPrivatekey(ActiveLedgerSDK.readFileAsString((Utility.PRIVATEKEY_FILE)));


                            if (key_Pair != null) {

                                onboardkeysTransaction(first_name, last_name, email,
                                        date_of_birth, phone_number, address, security, profile_type, gender, dp);


                            } else {
                                setShowToast("Generate Keys First");
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(KeyPair keyPair) {
                        setKey_Pair(keyPair);
                    }
                });
    }

    public void onboardkeysTransaction(String first_name, String last_name, String email,
                                       String date_of_birth, String phone_number, String address, String security, String profile_type, String gender, String dp) {


        ActiveLedgerSDK.KEYNAME = keyname;

        JSONObject onboardTransaction = onboardTransaction(key_Pair, ActiveLedgerSDK.getInstance().getKeyType(), first_name, last_name, email,
                date_of_birth, phone_number, address, security, profile_type, gender, dp);

        String transactionString = Utility.getInstance().convertJSONObjectToString(onboardTransaction);

        Utils.Log("Onboard Transaction", transactionString);
        Log.e("Onboard token", com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(context, PreferenceKeys.SP_APP_TOKEN, "null"));

        HttpClient.getInstance().createProfile(com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(context, PreferenceKeys.SP_APP_TOKEN, "null"), transactionString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        Log.e("onboard response--->", response.code() + "");
                        if (response.code() == 200) {

//                            try {
                            Utils.Log("onboard response--->", response.body() + "");

                            if (profileScreen != null) {
                                profileScreen.submitProfile();
                            }

//                                JSONObject responseJSON = new JSONObject(response.body());
//
//                                JSONObject stream = responseJSON.optJSONObject("stream");
//
//                                String first_name = stream.optString("first_name");
//                                String last_name = stream.optString("last_name");
//                                String email = stream.optString("email");
//                                String date_of_birth = stream.optString("date_of_birth");
//                                String phone_number = stream.optString("phone_number");
//                                String address = stream.optString("address");
//                                String security = stream.optString("security");
//                                String profile_type = stream.optString("profile_type");
//                                String gender = stream.optString("gender");
//                                String dp = stream.optString("dp");
//
//                                Log.e("onboard response--->", stream.optString("first_name")+ "");


//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }


                        }
                    }
                });


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
                pubKey = Utility.readFileAsString(Utility.PUBLICKEY_FILE);
                System.out.println("public:::" + pubKey.toString());
                String priKey = Utility.readFileAsString(Utility.PRIVATEKEY_FILE);
                System.out.println("public:::" + priKey.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

//            $tx.put("$contract", "695cf7b471bc01431c8dad2d4d00e64187f2e1eb09d2ca985238e4a186181ea8");
//            $tx.put("$contract", "4ef18a5639951bae4103b1dae3f0bc1d592fd7af66a10606afdabbf2f141d248");
            $tx.put("$contract", "263a140f90b72ca73c905906b33cc4b3955fdfe9e204b35142cc54d5e09d022a");
            $tx.put("$namespace", "dhealth");
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
                $sigs.put(ActiveLedgerSDK.KEYNAME, signMessage(signTransactionObject.getBytes(), keyPair, type));

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


}
