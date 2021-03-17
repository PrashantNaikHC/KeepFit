package com.hyperclock.instant.keepfit.model


import com.google.gson.annotations.SerializedName

data class ActivitiesResult(
    @SerializedName("activities")
    val activities: List<Activity>,
    @SerializedName("pagination")
    val pagination: Pagination
)