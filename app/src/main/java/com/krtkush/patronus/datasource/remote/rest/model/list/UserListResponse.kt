package com.krtkush.patronus.datasource.remote.rest.model.list

import com.squareup.moshi.Json

data class UserListResponse (
    @Json(name = "customers")
    var customers: List<Customer>
)