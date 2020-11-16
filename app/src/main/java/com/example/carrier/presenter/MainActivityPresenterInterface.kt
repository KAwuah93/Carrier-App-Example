package com.example.carrier.presenter

interface MainActivityPresenterInterface{
    interface Presenter {
        fun onDestroy()
    }
    interface View : BaseView<MainActivityPresenter>{
        fun updateUI()
    }
}