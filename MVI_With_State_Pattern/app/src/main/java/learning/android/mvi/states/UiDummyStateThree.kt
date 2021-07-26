package learning.android.mvi.states

import learning.android.mvi.MainActivityViewModel

class UiDummyStateThree(private val viewModel: MainActivityViewModel): UiState {

    override fun render() {
        viewModel.stateTitle.set("Dummy State 3")
        viewModel.stateDesc.set("Desc for dummy state 3")
        viewModel.isButtonOneEnabled.set(true)
        viewModel.isButtonTwoEnabled.set(true)
        viewModel.isButtonThreeEnabled.set(false)
    }
}