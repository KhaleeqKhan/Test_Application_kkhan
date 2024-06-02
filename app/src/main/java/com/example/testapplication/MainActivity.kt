package com.example.testapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
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
    private val useexactalarm = Manifest.permission.USE_EXACT_ALARM
    private val usefullscreenintent = Manifest.permission.USE_FULL_SCREEN_INTENT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                PermissionRequestScreen()
            }
        }
    }


    val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Berechtigung gewährt
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                // Berechtigung verweigert
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
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
            PermissionButton(permission = locationPermission, text = "Request Location Permission")
            Spacer(modifier = Modifier.height(8.dp))

            // Button für VIBRATE-Berechtigung
            PermissionButton(permission = vibratePermission, text = "Request Vibrate Permission")
            Spacer(modifier = Modifier.height(8.dp))

            // Button für READ_EXTERNAL_STORAGE-Berechtigung
            PermissionButton(permission = readExternalStoragePermission, text = "Request read storage Permission")
            Spacer(modifier = Modifier.height(8.dp))

            // Button für WRITE_EXTERNAL_STORAGE-Berechtigung
            PermissionButton(permission = writeExternalStoragePermission, text = "Request write storage Permission")
        }
    }

    @Composable
    fun PermissionButton(permission: String, text: String) {
        val permissionLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    // Berechtigung gewährt
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    // Berechtigung verweigert
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        Button(onClick = {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this , "Permission already granted", Toast.LENGTH_SHORT).show()
            } else {
                permissionLauncher.launch(permission)
            }
        })
        {
            Text(text)
        }
    }
}

