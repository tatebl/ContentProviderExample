package com.example.contentproviderexample.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.contentproviderexample.dao.DaoOperations
import com.example.contentproviderexample.dataModel.AppDatabase
import com.example.contentproviderexample.dataModel.Customer

class CustomerRepository(application: Application) {

    private lateinit var customerDao: DaoOperations

    init {
        var database = AppDatabase.getDatabase(application)
        customerDao = database.customerDao()
    }

    val readAllCustomers: LiveData<List<Customer>> = customerDao.fetchAllCustomer()

    suspend fun fetchCustomerById(id: Int): LiveData<Customer> {
        return customerDao.fetchCustomerById(id)
    }

    suspend fun deleteCustomerById(id: Int) {        //logic written in CustomerData
        customerDao.deleteCustomerById(id)
    }

    suspend fun insertCustomer(customer: Customer) {
        customerDao.insertCustomers(customer)
    }

}