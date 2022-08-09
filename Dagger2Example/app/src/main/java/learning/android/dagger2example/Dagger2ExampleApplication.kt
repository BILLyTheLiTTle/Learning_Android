package learning.android.dagger2example

import android.app.Application
import learning.android.dagger2example.di.components.DaggerApplicationComponent

class Dagger2ExampleApplication: Application() {
    val appComponent = DaggerApplicationComponent.factory().create(this)
}