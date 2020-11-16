package com.example.carrier.presenter

import android.util.Log
import com.example.carrier.model.data.ShiftDetailsResponse
import com.example.carrier.model.data.ShiftRequestMessage
import com.example.carrier.model.repos.ShiftDetailsService
import com.example.carrier.view.ShiftDetailsActivity
import kotlin.math.log

class MainActivityPresenter(private var activity: ShiftDetailsActivity?) : MainActivityPresenterInterface{
    var shiftData : ShiftDetailsResponse

    private val repository : ShiftDetailsService = ShiftDetailsService.createService()

    init {
        // check if there is
        this.shiftData = ShiftDetailsResponse(0,"started",0,"Joe Trucker")
    }

    fun updateShiftID(id: Int){
        shiftData.id = id
    }

    fun getShiftData(shiftID: Int) {
        // can use any amount of threading schemes one desires on this implementation. Left plain for now just for the flow of the program but can be 'customizable'
        repository.getShiftInfo(shiftID)
        Log.d("REPO", "getShiftData: ")

        // update the UI after you get the info that you are looking for.
        updateUI()
    }

    fun sendShiftMessage(message : String){
        val messageObject = ShiftRequestMessage(message)
        // maybe have a pass off to a slightly more complex class that tries to send message But will enqueue if it can't
        // and will wait for a 'good' signal
        repository.sendMessage(shiftData.id,messageObject)
        Log.d("REPO", "sendShiftMessage: sent $message")
    }

    override fun updateUI() {
        activity?.updateUI()
    }

    override fun onDestroy() {
        this.activity = null
    }
}