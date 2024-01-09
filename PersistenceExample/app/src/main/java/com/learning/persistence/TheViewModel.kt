package com.learning.persistence

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.learning.persistence.storage.ProtoBufDatastore
import com.learning.persistence.storage.SharedPrefDatastore
import com.learning.persistence.storage.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class TheViewModel(application: Application) : AndroidViewModel(application) {
    // Comment/Uncomment below lines to play with SharedPref/ProtoBuf DataStores
//    private val storage = SharedPrefDatastore(application.applicationContext)
    private val storage = ProtoBufDatastore(application.applicationContext)

    val user = storage.retrieve().debounce(300)

    fun updateUserData(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            storage.save(user)
        }
    }
}