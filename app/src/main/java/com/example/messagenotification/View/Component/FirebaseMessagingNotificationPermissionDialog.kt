package com.example.messagenotification.View.Component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FirebaseMessagingNotificationPermissionDialog(
    showNotificationDialog : MutableState<Boolean>,
    notificationPermissionState: PermissionState
) {
    if (showNotificationDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showNotificationDialog.value = false
                notificationPermissionState.launchPermissionRequest()
            },
            title = { Text(text = "Notification Permission") },
            text = { Text(text = "Please allow this application to send you a notification from firebase") },
            /*icon = {
                Icon(
                    painter = painterResource(id = R.drawable.snapchat),
                    contentDescription = null
                )
            },*/
            confirmButton = {
                TextButton(onClick = {
                    showNotificationDialog.value = false
                    notificationPermissionState.launchPermissionRequest()
                    Firebase.messaging.subscribeToTopic("Tutorial")
                }) {
                    Text(text = "Allow")
                }
            }
        )
    }
}
/*icon = {
    Icon(
        painterResource(id = R.drawable.playstore),
        contentDescription = "null"
    )
},*/