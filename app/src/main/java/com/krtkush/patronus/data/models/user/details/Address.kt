package com.krtkush.patronus.data.models.user.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Address(
    @Json(name = "city")
    val city: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "street")
    val street: String,
    @Json(name = "zip")
    val zip: String
)