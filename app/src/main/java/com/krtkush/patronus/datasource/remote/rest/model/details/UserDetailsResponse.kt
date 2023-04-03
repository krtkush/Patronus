package com.krtkush.patronus.datasource.remote.rest.model.details


import com.squareup.moshi.Json

data class UserDetailsResponse(
    @Json(name = "address")
    val address: Address,
    @Json(name = "currentLatitude")
    val currentLatitude: Double,
    @Json(name = "currentLongitude")
    val currentLongitude: Double,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "stickers")
    val stickers: List<String>
)