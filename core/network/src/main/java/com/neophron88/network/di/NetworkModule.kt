package com.neophron88.network.di

import com.neophron88.network.base.BaseUrl
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
    fun provideBaseUrl(): BaseUrl {
        return BaseUrl("https://guidebook.com")
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(baseUrl: BaseUrl): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl.url.plus("/"))
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .build()
    }
}