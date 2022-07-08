package com.example.bridgekyctest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    private val interceptor = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(20000L, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://aml-api-dev.rootone.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: Service = retrofit.create(Service::class.java)

}