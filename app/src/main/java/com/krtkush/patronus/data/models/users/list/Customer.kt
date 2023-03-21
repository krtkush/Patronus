package com.krtkush.patronus.data.models.users.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Customer(
    @Json(name = "id")
    var id: Int,

    @Json(name = "firstName")
    var firstName: String,

    @Json(name = "lastName")
    var lastName: String,

    @Json(name = "gender")
    var gender: String,

    @Json(name = "phoneNumber")
    var phoneNumber: String,

    @Json(name = "imageUrl")
    var imageUrl: String?,

    @Json(name = "stickers")
    var stickers: List<String>
)