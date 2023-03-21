package com.krtkush.patronus.data.models

import com.krtkush.patronus.data.models.Customer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class UserListResponseModel (
    @Json(name = "customers")
    var customers: List<Customer>
)