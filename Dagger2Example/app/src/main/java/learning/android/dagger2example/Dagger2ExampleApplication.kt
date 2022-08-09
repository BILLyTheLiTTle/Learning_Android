package learning.android.dagger2example

import android.app.Application
import learning.android.dagger2example.di.components.DaggerApplicationComponent

class Dagger2ExampleApplication: Application() {
    // Create the dagger graph and bond it with Application lifecycle.
    val appComponent = DaggerApplicationComponent.factory().create(this)
}