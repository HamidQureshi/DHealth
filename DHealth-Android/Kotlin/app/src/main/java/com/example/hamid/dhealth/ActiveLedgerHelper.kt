package com.example.hamid.dhealth

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.activeledgersdk.ActiveLedgerSDK
import com.example.activeledgersdk.ActiveLedgerSDK.signMessage
import com.example.activeledgersdk.model.Territoriality
import com.example.activeledgersdk.utility.KeyType
import com.example.activeledgersdk.utility.Utility
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.utils.Configurations
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.security.KeyPair
import java.util.*

class ActiveLedgerHelper {

    lateinit var preferenceManager: PreferenceManager
    private val disposable: Disposable? = null
    var key_Pair: KeyPair? = null
    var keyType = KeyType.RSA
    private var publickey: MutableLiveData<String>? = MutableLiveData()
    private var privatekey: MutableLiveData<String>? = MutableLiveData()
    var keyname = "activeledgerkey"
    private var onBoardId: MutableLiveData<String>? = null
    private var onBoardName: MutableLiveData<String>? = null
    private var showToast: MutableLiveData<String>? = MutableLiveData()
    private val loginApp = MutableLiveData<Boolean>()
    private var context: Context? = null


    fun setupALSDK(context: Context, preferenceManager: PreferenceManager) {
        this.preferenceManager = preferenceManager
        ActiveLedgerSDK.getInstance().initSDK(context, "http", "testnet-uk.activeledger.io", "5260")
        this.context = context
    }

    fun activityOnDestroy() {
        if (disposable != null && !disposable.isDisposed)
            disposable.dispose()
    }


    fun getPublickey(): MutableLiveData<String> {
        if (publickey == null) {
            publickey = MutableLiveData()
        }
        return publickey as MutableLiveData<String>
    }

    fun setPublickey(publickey: String) {
        this.publickey!!.postValue(publickey)
    }

    fun getPrivatekey(): MutableLiveData<String> {
        if (privatekey == null) {
            privatekey = MutableLiveData<String>()
        }
        return privatekey as MutableLiveData<String>
    }

    fun setPrivatekey(privatekey: String) {
        this.privatekey!!.postValue(privatekey)
    }

    fun getOnBoardId(): MutableLiveData<String> {
        if (onBoardId == null) {
            onBoardId = MutableLiveData()
        }
        return onBoardId as MutableLiveData<String>
    }

    fun setOnBoardId(onBoardId: String) {
        this.onBoardId!!.postValue(onBoardId)
    }

    fun getOnBoardName(): MutableLiveData<String> {
        if (onBoardName == null) {
            onBoardName = MutableLiveData()
        }
        return onBoardName as MutableLiveData<String>
    }

    fun setOnBoardName(onBoardName: String) {
        this.onBoardName!!.postValue(onBoardName)
    }

    fun getShowToast(): MutableLiveData<String> {
        if (showToast == null) {
            showToast = MutableLiveData()
        }
        return showToast as MutableLiveData<String>
    }

    fun setShowToast(message: String) {
        this.showToast!!.postValue(message)
    }

    fun loginApp(message: Boolean?) {
        this.loginApp.postValue(message)
    }

    fun getTerritorialityList() {
        ActiveLedgerSDK.getInstance().territorialityStatus
                .subscribe(object : Observer<Territoriality> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(response: Territoriality) {
                        //Territoriality object has a list of neighbours and reference to left and right node
                        Log.e("Territoriality --->", response.status)

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {}
                })
    }

    fun getTransactionData(id: String) {
        ActiveLedgerSDK.getInstance().getTransactionData(id)
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(response: String) {

                        Log.e("transaction data --->", response)

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {}
                })
    }

    // this method return the onboard transaction as JSON object
    fun onboardTransaction(type: KeyType, first_name: String, last_name: String, email: String,
                           date_of_birth: String, phone_number: String, address: String, security: String, profile_type: String, gender: String, dp: String): JSONObject {

        val transaction = JSONObject()
        val `$sigs` = JSONObject()
        val identity = JSONObject()
        val `$i` = JSONObject()
        val `$tx` = JSONObject()

        try {

            var pubKey: String? = null
            try {
                pubKey = Utility.readFileAsString(Utility.getPublicKeyFileName(email))
                println("public:::" + pubKey!!.toString())
                val priKey = Utility.readFileAsString(Utility.getPrivateKeyFileName(email))
                println("public:::" + priKey.toString())
            } catch (e: IOException) {
                e.printStackTrace()
            }

            `$tx`.put("\$contract", Configurations.contract)
            `$tx`.put("\$namespace", Configurations.namespace)
            `$tx`.put("\$entry", "create")

            identity.put("publicKey", pubKey)

            if (type == KeyType.RSA)
                identity.put("type", "rsa")
            else if (type == KeyType.EC)
                identity.put("type", "secp256k1")

            identity.put("first_name", first_name)
            identity.put("last_name", last_name)
            identity.put("email", email)
            identity.put("date_of_birth", date_of_birth)
            identity.put("phone_number", phone_number)
            identity.put("address", address)
            identity.put("security", security)
            identity.put("profile_type", profile_type)
            identity.put("gender", gender)
            identity.put("dp", dp)


            `$i`.put(ActiveLedgerSDK.KEYNAME, identity)
            `$tx`.put("\$i", `$i`)

            try {

                val signTransactionObject = Utility.getInstance().convertJSONObjectToString(`$tx`)
                `$sigs`.put(ActiveLedgerSDK.KEYNAME, signMessage(signTransactionObject.toByteArray(), null, type, email))

            } catch (e: Exception) {
                throw IllegalArgumentException("Unable to sign object:" + e.message)
            }


            transaction.put("\$tx", `$tx`)
            transaction.put("\$selfsign", true)
            transaction.put("\$sigs", `$sigs`)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return transaction
    }

    fun createUpdateUserTransaction(keyPair: KeyPair?, type: KeyType, first_name: String, last_name: String,
                                    date_of_birth: String, phone_number: String, address: String, dp: String, email: String): JSONObject {

        val transaction = JSONObject()
        val `$sigs` = JSONObject()
        val identity = JSONObject()
        val `$i` = JSONObject()
        val `$o` = JSONObject()
        val `$tx` = JSONObject()
//        if (preferenceManager == null) {
//            preferenceManager = PreferenceManager()
//        }
        val onboard_id = preferenceManager.readFromPref(context!!, PreferenceKeys.SP_IDENTITY, "null")

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


            `$tx`.put("\$contract", Configurations.contract)
            `$tx`.put("\$namespace", Configurations.namespace)
            `$tx`.put("\$entry", "update")

            //            identity.put("publicKey", pubKey);

            //            if (type == KeyType.RSA)
            //                identity.put("type", "rsa");
            //            else if (type == KeyType.EC)
            //                identity.put("type", "secp256k1");

            `$i`.put(onboard_id, identity)

            val updatedObject = JSONObject()
            updatedObject.put("first_name", first_name)
            updatedObject.put("last_name", last_name)
            updatedObject.put("date_of_birth", date_of_birth)
            updatedObject.put("phone_number", phone_number)
            updatedObject.put("address", address)
            updatedObject.put("dp", dp)

            `$o`.put(onboard_id, updatedObject)

            `$tx`.put("\$i", `$i`)
            `$tx`.put("\$o", `$o`)

            try {

                val signTransactionObject = Utility.getInstance().convertJSONObjectToString(`$tx`)
                `$sigs`.put(onboard_id, signMessage(signTransactionObject.toByteArray(), keyPair, type, email))

            } catch (e: Exception) {
                throw IllegalArgumentException("Unable to sign object:" + e.message)
            }


            transaction.put("\$tx", `$tx`)
            transaction.put("\$selfsign", false)
            transaction.put("\$sigs", `$sigs`)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return transaction
    }

    fun createUploadReportTransaction(keyPair: KeyPair?, type: KeyType, name: String, title: String, status: String, uploaddate: String, assignedto: String,
                                      signeddate: String, description: String, base64document: String, fileName: String, selected_array: ArrayList<String>, email: String): JSONObject {

        val transaction = JSONObject()
        val `$sigs` = JSONObject()
        val identity = JSONObject()
        val `$i` = JSONObject()
        val `$o` = JSONObject()
        val `$tx` = JSONObject()
//        if (preferenceManager == null) {
//            preferenceManager = PreferenceManager()
//        }
        val onboard_id = preferenceManager.readFromPref(context!!, PreferenceKeys.SP_IDENTITY, "null")

        try {

            `$tx`.put("\$contract", Configurations.report_contract_lbl)
            `$tx`.put("\$namespace", Configurations.namespace)
            `$tx`.put("\$entry", "upload")

            `$i`.put(onboard_id, identity)

            val reports_array = JSONArray()
            val report = JSONObject()

            report.put("patientName", name)
            report.put("title", title)
            report.put("doctors", JSONArray(selected_array))
            report.put("description", description)
            report.put("fileName", fileName)
            report.put("content", base64document)
            reports_array.put(report)
            `$o`.put(onboard_id, reports_array)

            `$tx`.put("\$i", `$i`)
            `$tx`.put("\$o", `$o`)

            try {

                val signTransactionObject = Utility.getInstance().convertJSONObjectToString(`$tx`)
                `$sigs`.put(onboard_id, signMessage(signTransactionObject.toByteArray(), keyPair, type, email))

            } catch (e: Exception) {
                throw IllegalArgumentException("Unable to sign object:" + e.message)
            }

            transaction.put("\$tx", `$tx`)
            transaction.put("\$selfsign", false)
            transaction.put("\$sigs", `$sigs`)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return transaction
    }

    fun createUpdateReportTransaction(keyPair: KeyPair, type: KeyType, name: String, title: String, status: String, uploaddate: String, assignedto: String,
                                      signeddate: String, description: String, base64document: String, fileName: String, selected_array: ArrayList<String>, email: String): JSONObject {

        val transaction = JSONObject()
        val `$sigs` = JSONObject()
        val identity = JSONObject()
        val `$i` = JSONObject()
        val `$o` = JSONObject()
        val `$tx` = JSONObject()
//        if (preferenceManager == null) {
//            preferenceManager = PreferenceManager()
//        }
        val onboard_id = preferenceManager.readFromPref(context!!, PreferenceKeys.SP_IDENTITY, "null")

        try {

            `$tx`.put("\$contract", Configurations.report_contract_lbl)
            `$tx`.put("\$namespace", Configurations.namespace)
            `$tx`.put("\$entry", "update")

            `$i`.put(onboard_id, identity)

            val reports_array = JSONArray()
            val report = JSONObject()

            //            report.put("patientName", name);
            //            report.put("title", title);
            report.put("doctors", JSONArray(selected_array))
            //            report.put("description", description);
            //            report.put("fileName", fileName);
            report.put("content", base64document)
            reports_array.put(report)
            `$o`.put(onboard_id, reports_array)

            `$tx`.put("\$i", `$i`)
            `$tx`.put("\$o", `$o`)

            try {

                val signTransactionObject = Utility.getInstance().convertJSONObjectToString(`$tx`)
                `$sigs`.put(onboard_id, signMessage(signTransactionObject.toByteArray(), keyPair, type, email))

            } catch (e: Exception) {
                throw IllegalArgumentException("Unable to sign object:" + e.message)
            }


            transaction.put("\$tx", `$tx`)
            transaction.put("\$selfsign", false)
            transaction.put("\$sigs", `$sigs`)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return transaction
    }

    companion object {

        internal var instance: ActiveLedgerHelper? = null

        fun getInstance(): ActiveLedgerHelper? {

            synchronized(ActiveLedgerHelper::class.java) {
                if (instance == null) {
                    instance = ActiveLedgerHelper()
                }
            }
            return instance
        }
    }


}
