package com.example.carrier.model.repos

import com.example.carrier.model.data.ShiftDetailsResponse
import com.example.carrier.model.data.ShiftRequestMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ShiftDetailsService {

    // this is where the calls for the class would go.
    @GET("shifts/{id}")
    fun getShiftInfo(@Path("id") shiftID: Int): Call<ShiftDetailsResponse>

    // Post with possible check for response object
    @POST("shifts/{id}/message")
    fun sendMessage(@Path("id") shiftID: Int, @Body message: ShiftRequestMessage): Call<ShiftRequestMessage>

    companion object {
        fun createService() : ShiftDetailsService = RetrofitHelper.getRetrofitInstance().create(
            ShiftDetailsService::class.java
        )
    }
}