package com.hyperclock.instant.keepfit.data

import com.hyperclock.instant.keepfit.data.network.FitbitClient
import com.hyperclock.instant.keepfit.model.ActivitiesResult
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val fitbitClient: FitbitClient
) {
    suspend fun getActivities(
        queries: Map<String, String>,
        authHeader: String
    ): Response<ActivitiesResult> {
        return fitbitClient.getActivities(queries, authHeader)
    }
}