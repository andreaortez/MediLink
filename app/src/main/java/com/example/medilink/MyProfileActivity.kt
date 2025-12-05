package com.example.medilink

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.lifecycleScope
import com.example.medilink.data.ApiClient
import com.example.medilink.ui.login.Login
import com.example.medilink.ui.perfil.ProfileOptionType
import com.example.medilink.ui.perfil.ProfileScreen
import kotlinx.coroutines.launch

class MyProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = SessionManager.getUserId(this)
        val name = SessionManager.getUserName(this)
        val Lname = SessionManager.getUserLastName(this)
        val type = SessionManager.getUserType(this)

        if (id == null || type == null) {
            finish()
            return
        }

        setContent {
            MaterialTheme {
                ProfileScreen(
                    userName = "$name $Lname",
                    type = type,
                    onBackClick = { finish() },
                    onOptionClick = { option ->
                        when (option) {
                            ProfileOptionType.VINCULATE -> {
                                startActivity(
                                    Intent(this, VincularFamiliarActivity::class.java)
                                )
                            }

                            ProfileOptionType.EDITPROFILE -> {
                                val intent = Intent(this, EditProfileActivity::class.java)
                                startActivity(intent)
                            }

                            ProfileOptionType.LIST -> {
                                startActivity(
                                    Intent(this, ListarUsuariosActivity::class.java)
                                )
                            }

                            ProfileOptionType.COPY -> {
                                val clipboard =
                                    getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("ID de usuario", id)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(
                                    this,
                                    "ID copiado al portapapeles",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            ProfileOptionType.LOGOUT -> {
                                doLogout()
                            }
                        }
                    }
                )
            }
        }
    }


    private fun doLogout() {
        lifecycleScope.launch {
            try {
                val response = ApiClient.userApi.logout()

                if (response.isSuccessful && (response.body()?.closed == true)) {

                    SessionManager.clearSession(this@MyProfileActivity)

                    Toast.makeText(
                        this@MyProfileActivity,
                        "Sesión cerrada",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@MyProfileActivity, Login::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@MyProfileActivity,
                        "No se pudo cerrar sesión",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@MyProfileActivity,
                    "Error al conectar con el servidor",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
