package learning.android.mvi

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val stateTitle = ObservableField<String>()
    val stateDesc = ObservableField<String>()
    val isButtonOneEnabled = ObservableField<Boolean>()
    val isButtonTwoEnabled = ObservableField<Boolean>()
    val isButtonThreeEnabled = ObservableField<Boolean>()

    init {
        stateTitle.set("Dummy state 1")
        stateDesc.set("Desc for dummy state 1")
        isButtonOneEnabled.set(false)
        isButtonTwoEnabled.set(true)
        isButtonThreeEnabled.set(true)
    }
}