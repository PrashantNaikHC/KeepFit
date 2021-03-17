package com.hyperclock.instant.keepfit.model


import com.google.gson.annotations.SerializedName
import com.hyperclock.instant.keepfit.model.ActivityLevel
import com.hyperclock.instant.keepfit.model.HeartRateZone
import com.hyperclock.instant.keepfit.model.ManualValuesSpecified
import com.hyperclock.instant.keepfit.model.Source

data class Activity(
    @SerializedName("activeDuration")
    val activeDuration: Int,
    @SerializedName("activityLevel")
    val activityLevel: List<ActivityLevel>,
    @SerializedName("activityName")
    val activityName: String,
    @SerializedName("activityTypeId")
    val activityTypeId: Int,
    @SerializedName("averageHeartRate")
    val averageHeartRate: Int,
    @SerializedName("calories")
    val calories: Int,
    @SerializedName("caloriesLink")
    val caloriesLink: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("distanceUnit")
    val distanceUnit: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("heartRateLink")
    val heartRateLink: String,
    @SerializedName("heartRateZones")
    val heartRateZones: List<HeartRateZone>,
    @SerializedName("lastModified")
    val lastModified: String,
    @SerializedName("logId")
    val logId: Int,
    @SerializedName("logType")
    val logType: String,
    @SerializedName("manualValuesSpecified")
    val manualValuesSpecified: ManualValuesSpecified,
    @SerializedName("pace")
    val pace: Double,
    @SerializedName("source")
    val source: Source,
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("steps")
    val steps: Int,
    @SerializedName("tcxLink")
    val tcxLink: String
)