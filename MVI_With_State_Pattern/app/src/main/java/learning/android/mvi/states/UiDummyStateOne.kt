package learning.android.mvi.states

import learning.android.mvi.MainActivityViewModel

class UiDummyStateOne(private val viewModel: MainActivityViewModel): UiState {

    override fun render() {
        viewModel.stateTitle.set("Dummy State 1")
        viewModel.stateDesc.set("Desc for dummy state 1")
        viewModel.isButtonOneEnabled.set(false)
        viewModel.isButtonTwoEnabled.set(true)
        viewModel.isButtonThreeEnabled.set(true)
    }
}