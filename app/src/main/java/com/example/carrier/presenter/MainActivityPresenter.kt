package com.example.carrier.presenter

import android.util.Log
import com.example.carrier.model.data.ShiftDetailsResponse
import com.example.carrier.model.data.ShiftRequestMessage
import com.example.carrier.model.data.ShiftStatusCode
import com.example.carrier.model.repos.ShiftDetailsService
import com.example.carrier.model.repos.localrepo.ShiftStatusCodeRepository
import com.example.carrier.view.ShiftDetailsActivity
import kotlinx.coroutines.*
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(private var view: MainActivityPresenterInterface.View?) : MainActivityPresenterInterface.Presenter{
    var shiftData : ShiftDetailsResponse

    private val retroRepository : ShiftDetailsService = ShiftDetailsService.createService()

    // You would typically figure out a way to get the context of the activity into the room database
    // best way to do this is dependency injection
    lateinit var localRepository : ShiftStatusCodeRepository


    init {
        // check if there is
        this.shiftData = ShiftDetailsResponse(0,"started",0,"Joe Trucker")
    }

    fun updateShiftID(id: Int){
        shiftData.id = id
    }

    fun getShiftData(shiftID: Int) {
         CoroutineScope(Dispatchers.IO).launch {
             val call = retroRepository.getShiftInfo(shiftID)
             call.enqueue( object : Callback<ShiftDetailsResponse> {
                 override fun onResponse(
                     call: retrofit2.Call<ShiftDetailsResponse>,
                     response: Response<ShiftDetailsResponse>
                 ) {
                     // place the data in the to the presenter's instance.
                     // query against the room to check the workstatus and use that status information
                     // place holder function/id

                     // data put just for the flow of the program's sake
                     // this is where actually pulled data would typically go
                     var dummyData = ShiftStatusCode(0,"scheduled")
                     localRepository.insertShiftStatusCode(dummyData)
                 }

                 override fun onFailure(call: retrofit2.Call<ShiftDetailsResponse>, t: Throwable) {
                     // have a failure method
                 }

             })
             val receivedData = call.isExecuted
             Log.d("REPO", "getShiftData: $receivedData")
         }


        //recievedData.isSuccessful
        //Log.d("REPO", "getShiftData: ${recievedData.isSuccessful}")

        // update the UI after you get the info that you are looking for.
        updateUI()
    }

    fun sendShiftMessage(message : String){
        val messageObject = ShiftRequestMessage(message)
        // maybe have a pass off to a slightly more complex class that tries to send message But will enqueue if it can't
        // and will wait for a 'good' signal
        retroRepository.sendMessage(shiftData.id,messageObject)
        Log.d("REPO", "sendShiftMessage: sent $message")
    }

    fun updateUI() {
        view?.updateUI()
    }

    override fun onDestroy() {

    }

}