package com.trentweet.fragmenttransition.data

class UserResponseModel(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<UserListModel>
)