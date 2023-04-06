package com.energyaustralia.di

import com.energyaustralia.data.api.ApiConstants
import com.energyaustralia.data.api.EnergyAustraliaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EnergyAustraliaApiModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): EnergyAustraliaApi {
        return builder
            .build()
            .create(EnergyAustraliaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


}