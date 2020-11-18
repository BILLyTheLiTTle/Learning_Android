package learning.android.bottomnav_mvi_navgraph.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    private val _originalNumber = MutableLiveData<Int>()
    val originalNumber: LiveData<Int> = _originalNumber

    init {
        _originalNumber.value = 0
    }

    fun stepIncrease() {
        _originalNumber.value = _originalNumber.value?.plus(1)
    }
}