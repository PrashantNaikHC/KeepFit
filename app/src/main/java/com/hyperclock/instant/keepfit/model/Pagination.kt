package com.hyperclock.instant.keepfit.model


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("beforeDate")
    val beforeDate: String,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("sort")
    val sort: String
)