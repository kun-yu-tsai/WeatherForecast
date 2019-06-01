package com.example.forecast.data.network

import com.example.forecast.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// This is going to be used in Retrofit
interface ApixApiService {

    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("lang") langCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApixApiService {

            val addQueryInterceptor = Interceptor { chain ->
                val addedQueryUrl = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", "value")
                    .build()

                val updatedUrlRequest = chain.request()
                    .newBuilder()
                    .url(addedQueryUrl)
                    .build()

                chain.proceed(updatedUrlRequest)
            }

            val okHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(addQueryInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("baseUrl")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApixApiService::class.java)
        }
    }
}