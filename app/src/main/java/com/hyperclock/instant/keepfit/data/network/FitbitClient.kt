package com.hyperclock.instant.keepfit.data.network

import android.app.Instrumentation
import com.hyperclock.instant.keepfit.model.ActivitiesResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FitbitClient {

    // https://api.fitbit.com/1/user/-/activities/list.json?offset=0&limit=20&sort=asc&afterDate=2019-01-01T12:00:00
    @GET("1/user/-/activities/list.json")
    suspend fun getActivities(
        @QueryMap queries: Map<String, String>,
        @Header("Authorization") authHeader: String
    ): Response<ActivitiesResult>

}