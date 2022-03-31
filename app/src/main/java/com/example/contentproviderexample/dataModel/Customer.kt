package com.example.contentproviderexample.dataModel

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,        //auto-generated id

    @ColumnInfo(name="name")
    val name:String?=null,
    @ColumnInfo(name="email_id")
    val emailId:String?=null
)