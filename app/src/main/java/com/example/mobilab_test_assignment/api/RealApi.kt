package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RealApi : Api {

    //If you want set up the server on your own system and run the application that way, this you must insert your own IP
    private val API_BASE_URL = "http://192.168.1.93:8080/"

    private val client: RestApiDescription

    init {
        val logging = HttpLoggingInterceptor()
        logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
        val httpClient = OkHttpClient.Builder().addInterceptor(logging)

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        client = builder
            .client(httpClient.build())
            .build().create(RestApiDescription::class.java)
    }

    override fun getLists(callback: Callback<List<ListModel>>) {
        client.getLists().enqueue(callback)
    }

    override fun getList(listId: Int, callback: Callback<ListModel>) {
        client.getList(listId).enqueue(callback)
    }

    override fun getItems(listId: Int, callback: Callback<List<ItemModel>>) {
        client.getItems(listId).enqueue(callback)
    }

    override fun addList(listModel: ListModel, callback: Callback<ListModel>) {
        client.addList(listModel).enqueue(callback)
    }

    override fun addItem(itemModel: ItemModel, callback: Callback<ItemModel>) {
        client.addItem(itemModel).enqueue(callback)
    }

    override fun deleteItem(itemId: Int, callback: Callback<Unit>) {
        client.deleteItem(itemId).enqueue(callback)
    }

    override fun deleteList(listId: Int, callback: Callback<Unit>) {
        client.deleteList(listId).enqueue(callback)
    }

    override fun changeItemStatus(itemId: Int, itemStatus: Boolean, callback: Callback<Unit>) {
        client.changeItemStatus(itemId, itemStatus).enqueue(callback)
    }
}