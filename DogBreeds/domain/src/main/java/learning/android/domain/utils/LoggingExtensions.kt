package learning.android.domain.utils

import android.util.Log
import kotlinx.coroutines.CoroutineName
import java.lang.Exception

/*
This is not possible at the time of this writing!
See relevant feature request: https://youtrack.jetbrains.com/issue/KT-11968
 */
//fun Log.coDebug() {
//    Log.d("", "")
//}

fun coLog(className: String?, functionName: String) {
    Log.d("${className ?: "?"}_$functionName", "Coroutine -> ${Thread.currentThread().name}")
}

fun coLog(coroutineName: CoroutineName) {
    Log.d(coroutineName.name, "Coroutine -> ${Thread.currentThread().name}")
}

fun erLog(className: String?, functionName: String, exception: Exception) {
    Log.e("${className ?: "?"}_$functionName", Log.getStackTraceString(exception))
}