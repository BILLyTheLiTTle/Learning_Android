package com.learning.persistence

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.persistence.storage.SharedPrefDatastore
import com.learning.persistence.storage.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TheViewModel(application: Application) : AndroidViewModel(application) {
    private val storage = SharedPrefDatastore(application.applicationContext)

    val user = storage.retrieve()

    fun updateUserData(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            storage.save(user)
        }
    }
}