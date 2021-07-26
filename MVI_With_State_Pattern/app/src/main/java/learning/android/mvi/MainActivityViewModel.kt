package learning.android.mvi

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import learning.android.mvi.states.UiDummyStateOne
import learning.android.mvi.states.UiDummyStateThree
import learning.android.mvi.states.UiDummyStateTwo
import learning.android.mvi.states.UiState

class MainActivityViewModel : ViewModel() {

    val stateTitle = ObservableField<String>()
    val stateDesc = ObservableField<String>()
    val isButtonOneEnabled = ObservableField<Boolean>()
    val isButtonTwoEnabled = ObservableField<Boolean>()
    val isButtonThreeEnabled = ObservableField<Boolean>()

    // States for MVI
    private val dummyStateOne = UiDummyStateOne(this)
    private val dummyStateTwo = UiDummyStateTwo(this)
    private val dummyStateThree = UiDummyStateThree(this)
    private val currentStateObservableField = ObservableField<UiState>()


    init {
        // react on MVI state change
        currentStateObservableField.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, i: Int) {
                currentStateObservableField.get()?.render()
            }
        })
        // initial MVI state
        currentStateObservableField.set(dummyStateOne)
    }

    fun getFirstDummyData() {
        fun onFirstDummyDataReceived() {
            currentStateObservableField.set(dummyStateOne)
        }
        // pretend to do something hard
        Thread.sleep(3000)
        onFirstDummyDataReceived()
    }

    fun getSecondDummyData() {
        fun onSecondDummyDataReceived() {
            currentStateObservableField.set(dummyStateTwo)
        }
        // pretend to do something harder
        Thread.sleep(4000)
        onSecondDummyDataReceived()
    }

    fun getThirdDummyData() {
        fun onThirdDummyDataReceived() {
            currentStateObservableField.set(dummyStateThree)
        }
        // pretend to do something much harder
        Thread.sleep(5000)
        onThirdDummyDataReceived()
    }
}