package learning.android.mvi.states

import learning.android.mvi.MainActivityViewModel

class UiDummyStateTwo(private val viewModel: MainActivityViewModel): UiState {

    override fun render() {
        viewModel.stateTitle.set("Dummy State 2")
        viewModel.stateDesc.set("Desc for dummy state 2")
        viewModel.isButtonOneEnabled.set(true)
        viewModel.isButtonTwoEnabled.set(false)
        viewModel.isButtonThreeEnabled.set(true)
    }
}