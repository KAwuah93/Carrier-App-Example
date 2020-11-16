package com.example.carrier.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carrier.R
import com.example.carrier.presenter.MainActivityPresenter
import com.example.carrier.presenter.MainActivityPresenterInterface

class ShiftDetailsActivity : AppCompatActivity(), MainActivityPresenterInterface {
    lateinit var presenter: MainActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        bindViews()

        presenter = MainActivityPresenter(this)
        // starting the call from the presenter
        // would use some primarykey information relevant to the data table but going with '0' as a place holder
        // could probably get the data from a login source when carried over to this activity.
        presenter.getShiftData(0)

    }

    private fun initView() {
        setContentView(R.layout.activity_shift_details)
    }

    private lateinit var statusView: TextView
    private lateinit var driverIdView: TextView
    private lateinit var driverNameView: TextView
    private lateinit var sendHelloButton: Button

    private fun bindViews() {
        statusView = findViewById(R.id.activity_shift_details_status)
        driverIdView = findViewById(R.id.activity_shift_details_driver_id)
        driverNameView = findViewById(R.id.activity_shift_details_driver_name)
        sendHelloButton = findViewById(R.id.activity_shift_details_button_hello)
    }

    fun onClick(v : View){
        // this is where the message goes. Right now we'll just feed it a dummy line
        // but we could use parameters to make sure we are sending the right data
        presenter.sendShiftMessage("Hello world!")
    }

    override fun updateUI() {
        //presenter.updateShiftID(0)
        statusView.text = presenter.shiftData.status
        driverIdView.text = presenter.shiftData.driverId.toString()
        driverNameView.text = presenter.shiftData.driverName.toString()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
