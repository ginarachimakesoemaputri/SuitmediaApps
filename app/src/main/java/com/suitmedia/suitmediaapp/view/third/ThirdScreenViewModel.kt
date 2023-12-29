package com.suitmedia.suitmediaapp.view.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.suitmedia.suitmediaapp.data.UserRepository
import com.suitmedia.suitmediaapp.data.response.DataItem

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    fun getUsers() : LiveData<PagingData<DataItem>> = repository.getAllUsers()

}