package org.tuwaiq.carwash.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    companion object{
        private  val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("localhost:3001")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getInstance(): Retrofit {
            return retrofit
        }

    }
}