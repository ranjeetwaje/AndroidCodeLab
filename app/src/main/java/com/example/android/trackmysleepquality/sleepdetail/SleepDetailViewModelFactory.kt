package com.example.android.trackmysleepquality.sleepdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import java.lang.IllegalArgumentException

class SleepDetailViewModelFactory(private val key: Long,
                                    private val database: SleepDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java)) {
            return SleepDetailViewModel(key, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}