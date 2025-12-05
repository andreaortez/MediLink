package com.example.medilink

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.rememberCoroutineScope
import com.example.medilink.data.ApiClient
import com.example.medilink.data.UpdateUserRequest
import com.example.medilink.ui.perfil.EditProfileScreen
import kotlinx.coroutines.launch

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = SessionManager.getUserId(this) ?: run {
            finish()
            return
        }

        val name = SessionManager.getUserName(this) ?: ""
        val lastName = SessionManager.getUserLastName(this) ?: ""
        val email = SessionManager.getUserEmail(this) ?: ""
        val phone = SessionManager.getUserPhone(this) ?: ""
        val age = SessionManager.getUserAge(this)?.toString() ?: ""

        setContent {
            MaterialTheme {
                val context = this
                val scope = rememberCoroutineScope()

                EditProfileScreen(
                    initialName = name,
                    initialLastName = lastName,
                    initialPhone = phone,
                    initialAge = age,
                    onBackClick = { finish() },
                    onSave = { newName, newLastName, newPhone, newAgeText ->
                        scope.launch {
                            try {
                                val ageInt = newAgeText.trim().toIntOrNull()
                                val phoneOrNull = newPhone.trim().ifBlank { null }

                                val body = UpdateUserRequest(
                                    _id = userId,
                                    correo = email,
                                    nombre = newName,
                                    apellido = newLastName,
                                    num_telefono = phoneOrNull,
                                    edad = ageInt
                                )

                                val response = ApiClient.userApi.updateUser(body)

                                if (response.isSuccessful) {
                                    SessionManager.setUserName(context, newName)
                                    SessionManager.setUserLastName(context, newLastName)
                                    SessionManager.setUserPhone(context, phoneOrNull)
                                    SessionManager.setUserAge(context, ageInt)

                                    Toast.makeText(
                                        context,
                                        "Perfil actualizado correctamente",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Error al actualizar perfil",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(
                                    context,
                                    "Error de conexión",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                )
            }
        }
    }
}
