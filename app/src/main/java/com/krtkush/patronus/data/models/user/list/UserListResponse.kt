package com.krtkush.patronus.data.models.user.list

import com.squareup.moshi.Json

data class UserListResponse (
    @Json(name = "customers")
    var customers: List<Customer>
)