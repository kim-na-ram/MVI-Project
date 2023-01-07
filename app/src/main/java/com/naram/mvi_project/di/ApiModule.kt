package com.naram.mvi_project.di

import com.naram.data.remote.NaverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    private const val BASE_URL = "https://openapi.naver.com/v1/search/"

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(Interceptor {
                    val request = it.request()
                        .newBuilder()
                        .addHeader("X-Naver-Client-Id", CLIEND_ID)
                        .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                        .build()
                    return@Interceptor it.proceed(request)
            })
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesDeliveryService(retrofit: Retrofit): NaverService {
        return retrofit.create(NaverService::class.java)
    }

}