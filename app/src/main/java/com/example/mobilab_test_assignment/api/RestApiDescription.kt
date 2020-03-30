package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Call
import retrofit2.http.GET

interface RestApiDescription {

    @GET("list")
    fun getLists(): Call<List<ListModel>>
}