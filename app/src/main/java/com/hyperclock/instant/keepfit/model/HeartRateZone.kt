package com.hyperclock.instant.keepfit.model


import com.google.gson.annotations.SerializedName

data class HeartRateZone(
    @SerializedName("max")
    val max: Int,
    @SerializedName("min")
    val min: Int,
    @SerializedName("minutes")
    val minutes: Int,
    @SerializedName("name")
    val name: String
)