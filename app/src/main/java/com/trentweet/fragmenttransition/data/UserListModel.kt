package com.trentweet.fragmenttransition.data

import java.io.Serializable

data class UserListModel(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
) : Serializable


