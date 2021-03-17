package com.hyperclock.instant.keepfit

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hyperclock.instant.keepfit.Constants.Companion.QUERY_DATE
import com.hyperclock.instant.keepfit.Constants.Companion.QUERY_LIMIT
import com.hyperclock.instant.keepfit.Constants.Companion.QUERY_OFFSET
import com.hyperclock.instant.keepfit.Constants.Companion.QUERY_SORT
import com.hyperclock.instant.keepfit.data.Repository
import com.hyperclock.instant.keepfit.model.ActivitiesResult
import com.hyperclock.instant.keepfit.model.AuthData
import com.hyperclock.instant.keepfit.utils.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class ActivityViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    var authData = AuthData()
    val activitiesResponse: MutableLiveData<NetworkResult<ActivitiesResult>> = MutableLiveData()

    fun getActivities(query: Map<String, String>, header: String) = viewModelScope.launch {
        getActivitiesSafeCall(query, header)
    }

    private suspend fun getActivitiesSafeCall(query: Map<String, String>, header: String) {
        activitiesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getActivities(query, header)
                activitiesResponse.value = handleActivitiesResponse(response)
                Log.d("ActivityViewModel", "getting n/w")
                activitiesResponse.value!!.data!!.activities.forEach {
                    Log.d("ActivityViewModel", it.calories.toString())
                }

                // add caching logic here
                /*val foodRecipe = activitiesResponse.value!!.data
                if(foodRecipe != null){
                    offlineCacheRecipes(foodRecipe)
                }*/
            } catch (e: Exception) {
                activitiesResponse.value = NetworkResult.Error("Recipes not found.")
            }
        } else {
            activitiesResponse.value = NetworkResult.Error("No internet connection")
        }
    }

    private fun handleActivitiesResponse(response: Response<ActivitiesResult>): NetworkResult<ActivitiesResult> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API key is limited")
            }
            response.body()!!.activities.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val activitiesResponse = response.body()
                return NetworkResult.Success(activitiesResponse!!)
            }
            else -> return NetworkResult.Error(response.message())
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun applyQueries(): Map<String, String> {
        val query: HashMap<String, String> = HashMap()
        query[QUERY_OFFSET] = "0"
        query[QUERY_LIMIT] = "20"
        query[QUERY_SORT] = "asc"
        query[QUERY_DATE] = "2019-01-01T12:00:00"
        return query
    }

    fun applyHeader(): String {
        Log.d("ActivityViewModel", "auth values : "+authData.tokenType + " " + authData.accessToken)
        return authData.tokenType + " " + authData.accessToken
    }
}