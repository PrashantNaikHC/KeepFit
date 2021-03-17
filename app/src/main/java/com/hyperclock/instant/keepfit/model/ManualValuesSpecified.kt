package com.hyperclock.instant.keepfit.model


import com.google.gson.annotations.SerializedName

data class ManualValuesSpecified(
    @SerializedName("calories")
    val calories: Boolean,
    @SerializedName("distance")
    val distance: Boolean,
    @SerializedName("steps")
    val steps: Boolean
)