package learning.android.compose.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GreetingsScreenViewModel: ViewModel() {
    private val _name = MutableLiveData("")
    val name : LiveData<String> = _name
    fun updateName(name: String) {
        _name.value = name
    }
}