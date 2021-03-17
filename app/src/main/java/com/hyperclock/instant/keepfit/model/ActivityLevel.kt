package com.hyperclock.instant.keepfit.model


import com.google.gson.annotations.SerializedName

data class ActivityLevel(
    @SerializedName("minutes")
    val minutes: Int,
    @SerializedName("name")
    val name: String
)