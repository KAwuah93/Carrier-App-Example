package com.example.carrier.model.repos

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {


    companion object {
        fun getRetrofitInstance() : Retrofit{

            //This is where you'll list out all of your static vals. Possible to pull this into another file
            val BASE_URL = "http://localhost.com/"

            // could extend this to have extra features as well as embed a header within here if it is needed
            // for my stubbed data it was just a dummy endpoint so none of that is required
            val client = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

//            fun<T> buildService(service: Class<T>) : T{
//                return retrofit.create(service)
//            }

        }

        fun getService() : ShiftDetailsService {
            return getRetrofitInstance().create(ShiftDetailsService::class.java)
        }
    }
}