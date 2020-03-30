package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RealApi : Api {

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

    override fun addItem(itemModel: ItemModel): ItemModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem(itemId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteList(listId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeItemStatus(itemId: Int, itemStatus: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}