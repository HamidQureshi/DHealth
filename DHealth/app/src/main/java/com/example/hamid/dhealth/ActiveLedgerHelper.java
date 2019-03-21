package com.example.hamid.dhealth;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;


import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.model.Territoriality;
import com.example.activeledgersdk.utility.KeyType;
import com.example.activeledgersdk.utility.PreferenceManager;
import com.example.activeledgersdk.utility.Utility;

import org.json.JSONException;

import java.io.IOException;
import java.security.KeyPair;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActiveLedgerHelper {


    static ActiveLedgerHelper instance = null;
    private Disposable disposable;
    private KeyPair key_Pair = null;
    private KeyType keyType = KeyType.RSA;
    private MutableLiveData<String> publickey = new MutableLiveData<>();
    private MutableLiveData<String> privatekey = new MutableLiveData<>();
    private String keyname = "activeledgerkey";
    private MutableLiveData<String> onBoardId;
    private MutableLiveData<String> onBoardName;
    private MutableLiveData<String> showToast = new MutableLiveData<>();

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
        generatekeys();
        //give some pause before calling that
//        onboardkeys();

    }


    public void generatekeys() {

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

    public void onboardkeys() {

        if (key_Pair != null) {

            ActiveLedgerSDK.getInstance().onBoardKeys(key_Pair, keyname)
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String response) {
                            try {
                                Utility.getInstance().extractID(response);

                                setOnBoardId(PreferenceManager.getInstance().getStringValueFromKey("onboard_id"));
                                setOnBoardName(PreferenceManager.getInstance().getStringValueFromKey("onboard_name"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.e("----->", response);

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {


                        }
                    });

        } else {
            setShowToast("Generate Keys First");
        }

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

    public void getTransactionData(String id){
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


}