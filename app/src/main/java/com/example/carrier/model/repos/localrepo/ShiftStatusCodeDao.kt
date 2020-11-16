package com.example.carrier.model.repos.localrepo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carrier.model.data.ShiftStatusCode

@Dao
interface ShiftStatusCodeDao {
    // basic queries, could be enhanced with the 'suspend' keyword to start taking advantage of the kotlin coroutines
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShiftStatusCode(shiftCode: ShiftStatusCode)

    @Query("SELECT * FROM shiftStatusCode_table where Id = :Id ")
    fun fetchShiftStatus(Id: Int)
}