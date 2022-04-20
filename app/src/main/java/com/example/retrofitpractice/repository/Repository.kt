package com.example.retrofitpractice.repository

import com.example.retrofitpractice.api.RetrofitInstance
import com.example.retrofitpractice.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

}