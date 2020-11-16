package com.example.carrier.presenter

interface BaseView<T> {
    fun selectPresenter(presenter : T)
}