package com.android.sergiobelda.gramophone.api

import com.android.sergiobelda.gramophone.data.Result
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

data class NetError(
    val statusCode: Int?,
    val error: String?
)

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): Result<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body())
            } else {
                val errorBody = response.errorBody()?.let {
                    Gson().fromJson(it.string(), NetError::class.java)
                }
                Result.Error(errorBody?.statusCode, errorBody?.error)
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException, is ConnectException -> Result.Error(
                    500, "", throwable
                )
                else -> Result.Error(500, "", throwable)
            }
        }
    }
}
