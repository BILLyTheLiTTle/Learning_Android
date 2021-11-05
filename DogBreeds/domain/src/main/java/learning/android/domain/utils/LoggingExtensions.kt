package learning.android.domain.utils

import android.util.Log
import java.lang.reflect.Method
import kotlin.reflect.KClass

/*
This is not possible at the time of this writing!
See relevant feature request: https://youtrack.jetbrains.com/issue/KT-11968
 */
//fun Log.coDebug() {
//    Log.d("", "")
//}

fun coDebug(className: String?, functionName: String) {
    Log.d("${className ?: "?"}_$functionName", "Coroutine -> ${Thread.currentThread().name}")
}