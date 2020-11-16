package com.example.carrier.model.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shiftStatusCode_table")
data class ShiftStatusCode(
    @PrimaryKey @ColumnInfo(name = "Id") var Id : Int,
    @ColumnInfo(name = "status") val status : String) : Parcelable