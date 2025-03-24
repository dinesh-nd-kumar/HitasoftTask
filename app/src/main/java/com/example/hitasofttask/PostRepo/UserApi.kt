package com.example.hitasofttask.PostRepo

import com.example.hitasofttask.model.RequUser
import com.example.hitasofttask.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @DELETE("users/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit>

    @POST("users")
    suspend fun newPost(@Body p: RequUser) : Response<User>

    @PUT("users/{id}")
    suspend fun updatePost(@Body p: User, @Path("id") id:Int) : Response<User>

}