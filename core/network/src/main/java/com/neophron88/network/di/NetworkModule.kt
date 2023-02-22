package com.neophron88.network.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
internal class NetworkModule {


    private fun getMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(" https://guidebook.com/")
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .build()
    }
}