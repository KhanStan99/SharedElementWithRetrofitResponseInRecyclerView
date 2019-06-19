package com.trentweet.fragmenttransition.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {

    private val baseURL = "https://reqres.in/api/"
    private var retrofit: Retrofit? = null
    private val REQUEST_TIMEOUT = 60


    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

}