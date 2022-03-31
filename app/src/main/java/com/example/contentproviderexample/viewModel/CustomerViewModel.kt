package com.example.contentproviderexample.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contentproviderexample.dataModel.Customer
import com.example.contentproviderexample.repository.CustomerRepository
import kotlinx.coroutines.launch

class CustomerViewModel(appObj: Application): AndroidViewModel(appObj) {

    private val customerRepository: CustomerRepository = CustomerRepository(appObj)

    fun fetchAllCustomers(): LiveData<List<Customer>> {
        return customerRepository.readAllCustomers
    }

    suspend fun fetchCustomerById(id: Int): LiveData<Customer> {
        return customerRepository.fetchCustomerById(id)
    }

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.insertCustomer(customer)
        }
    }

    fun deleteCustomerById(id: Int) {
        viewModelScope.launch {
            customerRepository.deleteCustomerById(id)
        }
    }
}