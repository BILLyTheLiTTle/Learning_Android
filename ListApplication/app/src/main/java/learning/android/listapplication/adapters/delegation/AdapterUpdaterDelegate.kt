package learning.android.listapplication.adapters.delegation

import android.util.Log
import androidx.recyclerview.widget.AsyncListDiffer
import kotlin.reflect.KProperty

class AdapterUpdaterDelegate<T>(private val differ: AsyncListDiffer<T>, private var value: List<T>) {

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): List<T> {
        return value
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, newValue: List<T>) {
        Log.v("Items (Delegation)", "$newValue")
        value = newValue
        differ.submitList(value)
    }
}