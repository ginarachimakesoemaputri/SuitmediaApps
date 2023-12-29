package com.suitmedia.suitmediaapp.di

import android.content.Context
import com.suitmedia.suitmediaapp.data.UserRepository
import com.suitmedia.suitmediaapp.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}