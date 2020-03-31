package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Call
import retrofit2.http.*

interface RestApiDescription {

    @GET("list")
    fun getLists(): Call<List<ListModel>>

    @GET("list/{listId}")
    fun getList(@Path("listId") listId: Int): Call<ListModel>

    @GET("item/{listId}")
    fun getItems(@Path("listId") listId: Int): Call<List<ItemModel>>

    @PUT("list")
    fun addList(@Body listModel: ListModel): Call<ListModel>

    @PUT("item")
    fun addItem(@Body itemModel: ItemModel): Call<ItemModel>

    @DELETE("item/{itemId}")
    fun deleteItem(@Path("itemId") itemId: Int): Call<Unit>

    @DELETE("list/{listId}")
    fun deleteList(@Path("listId") listId: Int): Call<Unit>

    @POST("item/{itemId}")
    fun changeItemStatus(@Path("itemId") itemId: Int, @Query("itemStatus") itemStatus: Boolean): Call<Unit>
}