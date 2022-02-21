package com.example.pics.data.network

import com.example.pics.data.network.mapper.ResponseMapper
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

fun <RESPONSE, OUTPUT> getOutput(
    response: Response<RESPONSE>,
    mapper: ResponseMapper<RESPONSE, OUTPUT?>
): Request<OUTPUT> {
    return if (response.isSuccessful) {
        val output = response.body()?.let { mapper.modelFromResponse(it) }
        if (output != null) {
            Request.Success(output)
        } else {
            Request.Error.InsufficientData
        }
    } else {
        val code = response.code()
        val message = getErrorObj(response)?.let { obj ->
            getErrorMessage(obj)
        } ?: "Request failed!"
        Request.Error.FailedResponse(message, code)
    }
}

fun getErrorMessage(jsonObject: JSONObject): String? {
    return try {
        jsonObject.getString("detail")
    } catch (ex: Exception) {
        Timber.d("request getErrorMessage: %s", ex.message)
        null
    }
}

fun getErrorObj(response: Response<*>): JSONObject? {
    return try {
        response.errorBody()?.string()?.let { JSONObject(it) }
    } catch (ex: Exception) {
        Timber.d("request getErrorObj: %s", ex.message)
        null
    }
}
