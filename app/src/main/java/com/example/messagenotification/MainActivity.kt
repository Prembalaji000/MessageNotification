package com.example.messagenotification

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.messagenotification.View.FirebaseMessagingScreen
import com.example.messagenotification.ui.theme.MessageNotificationTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful){
                Log.w("Failed", "fetching token registration token failed ", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("Token", token.toString())
            Toast.makeText(baseContext,token.toString(), Toast.LENGTH_SHORT).show()
        })
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessageNotificationTheme {
                FirebaseMessagingScreen()
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MessageNotificationTheme {
        FirebaseMessagingScreen()
    }
}
