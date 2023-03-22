package com.krtkush.patronus.data.models.user.details

import com.squareup.moshi.Json

data class UserDetailsResponse(
    @Json(name = "firstName")
    var firstName: String,

    @Json(name = "lastName")
    var lastName: String,
)