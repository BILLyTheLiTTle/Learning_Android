package learning.android.bottomnav_mvi_navgraph.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _originalNumber = MutableLiveData<Int>()
    val originalNumber: LiveData<Int>
        get() = _originalNumber

    init {
        _originalNumber.value = 1
    }

}