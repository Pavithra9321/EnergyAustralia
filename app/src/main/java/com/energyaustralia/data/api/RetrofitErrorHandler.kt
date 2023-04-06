package com.energyaustralia.data.api

import retrofit2.Response

object RetrofitErrorHandler {

    fun <T> retrofitErrorHandler(res: Response<T>): T {
        if (res.isSuccessful) {
            return res.body()!!
        } else {
            var errMsg = when (res.code()) {
                429 -> "Too many requests, Please try again later"
                else -> "Please try again later"
            }
            throw Exception(errMsg)
        }
    }

}