package com.example.umc_velog_aos.application

import android.app.Application
import com.example.umc_velog_aos.data.module.DataStoreModule

class ApplicationClass : Application() {
    private lateinit var dataStore : DataStoreModule

    companion object {
        private lateinit var applicationClass: ApplicationClass
        fun getInstance() : ApplicationClass = applicationClass
    }

    override fun onCreate() {
        super.onCreate()
        applicationClass = this
        dataStore = DataStoreModule(this)
    }
    fun getDataStore() : DataStoreModule = dataStore
}