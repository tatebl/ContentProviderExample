package com.example.contentproviderexample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contentproviderexample.dataModel.Customer

@Dao
interface DaoOperations {
    @Query("SELECT * FROM customer")
    fun fetchAllCustomer(): LiveData<List<Customer>>

    @Query("SELECT * FROM customer WHERE id=:id")
    fun fetchCustomerById(id:Int): LiveData<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomers(customer:Customer)

    @Query("DELETE FROM customer WHERE id=:id")
    suspend fun deleteCustomerById(id:Int)

}