package com.example.carrier.model.repos.localrepo

import com.example.carrier.model.data.ShiftStatusCode

class ShiftStatusCodeRepository (private val shiftStatusCodeDao: ShiftStatusCodeDao){
    fun insertShiftStatusCode(shiftStatusCode: ShiftStatusCode) {
        shiftStatusCodeDao.insertShiftStatusCode(shiftStatusCode)
    }

    fun fetchShiftStatus(Id : Int){
        shiftStatusCodeDao.fetchShiftStatus(Id)
    }
}