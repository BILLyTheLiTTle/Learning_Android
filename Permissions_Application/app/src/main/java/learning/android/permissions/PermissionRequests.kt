package learning.android.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val PERMISSION_REQUEST_CODE = 1234

fun checkAndRequestPermissions(activity: Activity, permissions: Array<String>): Boolean {
    val permissionsRemaining = mutableListOf<String>()

    // check if permissions already granted
    permissions.forEach {
        if (ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED) {
            permissionsRemaining.add(it)
        }
    }

    // Request permissions for non granted
    if (permissionsRemaining.isNotEmpty()) {
        ActivityCompat.requestPermissions(activity, permissionsRemaining.toTypedArray(), PERMISSION_REQUEST_CODE)
        return false
    }
    return true
}