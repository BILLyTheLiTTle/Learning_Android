package learning.android.permissions

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val appPermissions = arrayOf(
        Manifest.permission.WRITE_CALENDAR,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.SEND_SMS,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val appPermissionsDesc = mapOf<String, String>(
        Manifest.permission.WRITE_CALENDAR to "Needs to mark a special date in Calendar.",
        Manifest.permission.CAMERA to "Needs to spy you from the Camera.",
        Manifest.permission.READ_CONTACTS to "Appication needs new friends! Your Contacts.",
        Manifest.permission.ACCESS_FINE_LOCATION to "Need to know your exact Location",
        Manifest.permission.ACCESS_COARSE_LOCATION to "Need to know at least approximately your Location",
        Manifest.permission.CALL_PHONE to "Need to Call my uncle at North Pole",
        Manifest.permission.SEND_SMS to "In case I could not find my uncle at least let me send an SMS",
        Manifest.permission.WRITE_EXTERNAL_STORAGE to "Do you have any interesting for me in your Storage?"
    )

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.status_textview)
        textView.text = "Permissions Missing"
        if (checkAndRequestPermissions(this, appPermissions)) {
            initApp()
        }
    }

    private fun initApp() {
        textView.text = "Permissions Granted"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val permissionsResults = mutableMapOf<String, Int>()
            var permissionsDeniedCounter = 0
            var permissionsPermanentlyDeniedCounter = 0

            // detect the denied permissions
            grantResults.forEachIndexed { index, value ->
                if (value == PackageManager.PERMISSION_DENIED) {
                    permissionsResults[permissions[index]] = value
                    permissionsDeniedCounter++
                }
            }

            if (permissionsDeniedCounter == 0) {
                initApp()
            }
            else {
                var requestPermissionDesc = ""
                var settingsPermissionDesc = ""
                // for every denied permission give a second chance to the user (1)...
                permissionsResults.forEach { (permissionName, permissionResult) ->
                    //(1)... to grant permission
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionName)) {
                        requestPermissionDesc = requestPermissionDesc.plus("$permissionName: ${appPermissionsDesc[permissionName]}\n")
                    }
                    //(1)... or reject it forever (till open settings screen and grant from there)
                    else {
                        permissionsPermanentlyDeniedCounter++
                        settingsPermissionDesc = settingsPermissionDesc.plus("$permissionName: ${appPermissionsDesc[permissionName]}\n")
                    }
                }
                showRationaleDialog("Re-request Permissions",
                    "From permission dialog:\n" +
                            requestPermissionDesc +
                            "\nFrom settings:\n" +
                            settingsPermissionDesc)
            }

        }
    }

    private fun showRationaleDialog(title: String, desc: String) {
        showDialog(title, desc,
            "Request",
            { dialogInterface, i ->
                dialogInterface.dismiss()
                checkAndRequestPermissions(this, appPermissions)},
            "Settings",
            { dialogInterface, i ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)}, true)
    }

    private fun showDialog(title:String, msg:String,
                           positiveLabel: String, positiveOnClick: DialogInterface.OnClickListener,
                           negativeLabel: String, negativeOnClick: DialogInterface.OnClickListener,
                           isCancelable: Boolean) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setCancelable(isCancelable)
            .setMessage(msg)
            .setPositiveButton(positiveLabel, positiveOnClick)
            .setNegativeButton(negativeLabel, negativeOnClick).create().show()
    }
}