package com.example.medilink.ui.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medilink.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    initialName: String,
    initialLastName: String,
    initialPhone: String,
    initialAge: String,
    onBackClick: () -> Unit,
    onSave: (String, String, String, String) -> Unit // name, lastName, phone, ageText
) {
    var name by remember { mutableStateOf(initialName) }
    var lastName by remember { mutableStateOf(initialLastName) }
    var phone by remember { mutableStateOf(initialPhone) }
    var ageText by remember { mutableStateOf(initialAge) }

    // Azul oscuro de la app
    val primaryBlue = Color(0xFF012248)
    val gradientOverlay = Brush.verticalGradient(
        listOf(Color(0x40012248), Color(0x80012248))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar perfil", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryBlue
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Fondo bg3
            Image(
                painter = painterResource(id = R.drawable.bg3),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Ligero overlay para que se lea bien el card
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientOverlay)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Información personal",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryBlue
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryBlue,
                            unfocusedBorderColor = primaryBlue,
                            focusedLabelColor = primaryBlue,
                            cursorColor = primaryBlue
                        )
                    )

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Apellido") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryBlue,
                            unfocusedBorderColor = primaryBlue,
                            focusedLabelColor = primaryBlue,
                            cursorColor = primaryBlue
                        )
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryBlue,
                            unfocusedBorderColor = primaryBlue,
                            focusedLabelColor = primaryBlue,
                            cursorColor = primaryBlue
                        )
                    )

                    OutlinedTextField(
                        value = ageText,
                        onValueChange = { ageText = it.filter { ch -> ch.isDigit() } },
                        label = { Text("Edad") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryBlue,
                            unfocusedBorderColor = primaryBlue,
                            focusedLabelColor = primaryBlue,
                            cursorColor = primaryBlue
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { onSave(name, lastName, phone, ageText) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryBlue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Guardar cambios", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
