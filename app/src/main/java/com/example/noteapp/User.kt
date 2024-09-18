package com.example.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid : Int =0,
    @ColumnInfo(name = "NAME")
    var name : String,
    @ColumnInfo(name = "E-MAIL")
    var email :String,
    @ColumnInfo(name = "PHONE")
    var phone :String

)


