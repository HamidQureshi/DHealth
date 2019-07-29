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
package com.example.hamid.dhealth.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {


    @Headers("Content-Type: application/json")
    @POST("/register")
    fun registerUser(@Body post: String): Single<Response<String>>

    @Headers("Content-Type: application/json")
    @POST("/login")
    fun loginUser(@Header("Authorization") authHeader: String): Single<Response<String>>

    @Headers("Content-Type: application/json")
    @POST("/transaction/createProfile")
    fun createProfile(@Header("Authorization") token: String, @Body profile: String): Single<Response<String>>

    @Headers("Content-Type: application/json")
    @POST("/transaction")
    fun sendTransaction(@Header("Authorization") token: String, @Body profile: String): Single<Response<String>>

    @Headers("Content-Type: application/json")
    @GET("/transaction/users/doctors")
    fun getDoctorList(@Header("Authorization") token: String): Single<Response<String>>

    @Headers("Content-Type: application/json")
    @GET("/transaction/users/patients")
    fun getPatientList(@Header("Authorization") token: String): Single<Response<String>>

    @Headers("Content-Type: application/json")
    @GET("/transaction/patients")
    fun getAssignedPatientList(@Header("Authorization") token: String): Single<Response<String>>

    @Headers("Content-Type: application/json")
    @GET("/transaction/reports")
    fun getReport(@Header("Authorization") token: String): Single<Response<String>>


}