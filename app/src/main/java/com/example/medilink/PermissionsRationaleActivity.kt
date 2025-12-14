package com.example.medilink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity

class PermissionsRationaleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val url = "https://dominio.com/privacy"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

        finish()
    }
}


