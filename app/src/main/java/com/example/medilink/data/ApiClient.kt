package com.example.medilink.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

object ApiClient {

    private const val BASE_URL = "http://10.0.2.2:3000/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val chatApi: ChatApi by lazy {
        retrofit.create(ChatApi::class.java)
    }

    val userApi: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
}

data class UserDto(
    val _id: String,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val num_telefono: String?,
    val edad: Int?,
    val tipoUsuario: String?
)

data class UpdateUserRequest(
    val _id: String,
    val correo: String,
    val nombre: String,
    val apellido: String,
    val num_telefono: String?,
    val edad: Int?
)

interface UserApi {

    @PUT("users/updateUser")
    suspend fun updateUser(
        @Body body: UpdateUserRequest
    ): Response<UserDto>

    @POST("users/logout")
    suspend fun logout(): Response<LogoutResponse>
}

data class LogoutResponse(
    val message: String?,
    val closed: Boolean?
)