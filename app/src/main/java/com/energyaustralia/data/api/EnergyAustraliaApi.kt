package com.energyaustralia.data.api

import com.energyaustralia.data.api.model.MusicFestival
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface EnergyAustraliaApi {

    @Headers("Accept: text/plain")
    @GET(ApiConstants.FESTIVALS)
    suspend fun getMusicFestivals(): Response<List<MusicFestival>>
}