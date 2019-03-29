/*
 * MIT License (MIT)
 * Copyright (c) 2018
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.example.hamid.dhealth.MedicalRepository.HTTP;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class HttpClient {

    private static HttpClient instance;
    private APIService apiService;

    // this method creates and HttpClient that is further user to execute transactions
    private HttpClient() {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://testnet-uk.activeledger.io:5555")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            instance = new HttpClient();
        }
        return instance;
    }


    public Observable<Response<String>> registerUser(String user) {
        return apiService.registerUser(user);
    }

    public Observable<Response<String>> loginUser(String user) {
        return apiService.loginUser(user);
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    public Observable<Response<String>> createProfile(String token, String transaction) {
        return apiService.createProfile(token, transaction);
    }


    // this method can be used to send transaction as an HTTP request to the ledger
    public Observable<Response<String>> sendTransaction(String token, String transaction) {
        return apiService.sendTransaction(token, transaction);
    }

    public Observable<Response<String>> getDoctorListFromServer(String token) {
        return apiService.getDoctorList(token);
    }

    public Observable<Response<String>> getPatientListFromServer(String token) {
        return apiService.getPatientList(token);
    }


}
