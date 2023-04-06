package com.energyaustralia.data.repository

import com.energyaustralia.data.api.EnergyAustraliaApi
import com.energyaustralia.data.api.model.MusicFestival
import retrofit2.Response
import javax.inject.Inject

class MusicFestivalRepo @Inject constructor(
    private val energyAustraliaApi: EnergyAustraliaApi
) {
    suspend fun getMusicFestivals(): Response<List<MusicFestival>> {
        return energyAustraliaApi.getMusicFestivals()
    }
}