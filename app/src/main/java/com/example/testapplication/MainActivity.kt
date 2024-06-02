package com.example.testapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.testapplication.ui.theme.TestApplicationTheme


class MainActivity : ComponentActivity() {

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val wakeLockPermission = Manifest.permission.WAKE_LOCK
    private val vibratePermission = Manifest.permission.VIBRATE
    private val readExternalStoragePermission = Manifest.permission.READ_EXTERNAL_STORAGE
    private val writeExternalStoragePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val receiveBootCompletedPermission = Manifest.permission.RECEIVE_BOOT_COMPLETED
    private val accessNotificationPolicyPermission = Manifest.permission.ACCESS_NOTIFICATION_POLICY
    private val postNotificationsPermission = Manifest.permission.POST_NOTIFICATIONS
    private val foregroundServicePermission = Manifest.permission.FOREGROUND_SERVICE
    private val notificationPolicyPermission = "android.permission.NOTIFICATION_POLICY"


    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Berechtigung gewährt
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            // Berechtigung verweigert
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                PermissionRequestScreen()
            }
        }
    }

    @Composable
    fun PermissionRequestScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Button für Standort-Berechtigung
            Button(onClick = { requestPermission(locationPermission) }) {
                Text("Request Location Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Button für VIBRATE-Berechtigung
            Button(onClick = { requestPermission(vibratePermission) }) {
                Text("Request VIBRATE Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Button für READ_EXTERNAL_STORAGE-Berechtigung
            Button(onClick = { requestPermission(readExternalStoragePermission) }) {
                Text("Request READ_EXTERNAL_STORAGE Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Button für WRITE_EXTERNAL_STORAGE-Berechtigung
            Button(onClick = { requestPermission(writeExternalStoragePermission) }) {
                Text("Request WRITE_EXTERNAL_STORAGE Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

        /*

        // Button für WAKE_LOCK-Berechtigung
            Button(onClick = { requestPermission(wakeLockPermission) }) {
                Text("Request WAKE_LOCK Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

        // Button für RECEIVE_BOOT_COMPLETED-Berechtigung
            Button(onClick = { requestPermission(receiveBootCompletedPermission) }) {
                Text("Request RECEIVE_BOOT_COMPLETED Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

        // Button für NOTIFICATION_POLICY-Berechtigung
            Button(onClick = { requestPermission(notificationPolicyPermission) }) {
                Text("Request NOTIFICATION_POLICY Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

        // Button für ACCESS_NOTIFICATION_POLICY-Berechtigung
            Button(onClick = { requestPermission(accessNotificationPolicyPermission) }) {
                Text("Request ACCESS_NOTIFICATION_POLICY Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))

        // Button für POST_NOTIFICATIONS-Berechtigung
            Button(onClick = { requestPermission(postNotificationsPermission) }) {
                Text("Request POST_NOTIFICATIONS Permission")

        // Button für FOREGROUND_SERVICE-Berechtigung
            Button(onClick = { requestPermission(foregroundServicePermission) }) {
                Text("Request FOREGROUND_SERVICE Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))
         */

            }
        }


    private fun requestPermission(permission: String) {
        if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        } else {
            permissionLauncher.launch(permission)
        }
    }



}


/*
  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults)

      for ((index, permission) in permissions.withIndex()) {
          if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
              Toast.makeText(this, "$permission granted", Toast.LENGTH_SHORT).show()
          } else {
              Toast.makeText(this, "$permission denied", Toast.LENGTH_SHORT).show()
          }
      }
  }
*/
