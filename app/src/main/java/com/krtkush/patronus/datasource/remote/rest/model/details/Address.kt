package com.krtkush.patronus.datasource.remote.rest.model.details


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