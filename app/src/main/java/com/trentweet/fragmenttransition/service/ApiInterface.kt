package com.trentweet.fragmenttransition.service

import com.trentweet.fragmenttransition.data.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {

    @GET("users")
    fun getAllUsers(): Call<UserResponseModel>
}
