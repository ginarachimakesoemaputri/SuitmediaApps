package com.suitmedia.suitmediaapp.view.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.suitmedia.suitmediaapp.data.UserRepository
import com.suitmedia.suitmediaapp.data.response.DataItem

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    fun getUsers() : LiveData<PagingData<DataItem>> = repository.getAllUsers()

    fun refreshData() {
        _isRefreshing.value = true

        repository.refreshData(object : UserRepository.RefreshCallback {
            override fun onRefreshComplete() {
                _isRefreshing.value = false
            }
        })
    }
}