package org.tuwaiq.carwash.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor




class Api {



    companion object{
        private fun getClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return client
        }

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://shiny-wash.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient()) // logger for api
            .build()

        fun getInstance(): Retrofit {
            return retrofit
        }

    }
}