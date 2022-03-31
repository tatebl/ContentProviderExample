package com.example.contentproviderexample.dataModel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contentproviderexample.dao.DaoOperations

@Database(entities = [Customer::class],version = 1,exportSchema=false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun customerDao():DaoOperations     //pointer
    companion object {

        @Volatile    //anything that you right to INSTANCE will be available anywhere in app
        private var INSTANCE: AppDatabase?=null;

        fun getDatabase(context: Context):AppDatabase {    //returns database
            val tempInstance = INSTANCE
            if(tempInstance!=null) {
                return tempInstance
            }

            synchronized(this) {        //will lock the other objects to allow this function to
                //executes
                var instance= Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,"Brandon").build()

                INSTANCE = instance

                return instance

            }
        }
    }

}