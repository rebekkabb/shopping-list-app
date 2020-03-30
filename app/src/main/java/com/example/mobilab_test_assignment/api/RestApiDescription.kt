package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestApiDescription {

    @GET("list")
    fun getLists(): Call<List<ListModel>>

    @GET("list/{listId}")
    fun getList(@Path("listId") listId: Int): Call<ListModel>

    @GET("item/{listId}")
    fun getItems(@Path("listId") listId: Int): Call<List<ItemModel>>

    @PUT("list")
    fun addList(@Body listModel: ListModel): Call<ListModel>
}