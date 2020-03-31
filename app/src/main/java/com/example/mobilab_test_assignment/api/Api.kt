package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Callback

interface Api {
    fun getLists(callback: Callback<List<ListModel>>)
    fun getList(listId: Int, callback: Callback<ListModel>)
    fun getItems(listId: Int, callback: Callback<List<ItemModel>>)
    fun addList(listModel: ListModel, callback: Callback<ListModel>)
    fun addItem(itemModel: ItemModel, callback: Callback<ItemModel>)
    fun deleteItem(itemId: Int, callback: Callback<Unit>)
    fun deleteList(listId: Int, callback: Callback<Unit>)
    fun changeItemStatus(itemId: Int, itemStatus: Boolean, callback: Callback<Unit>)
}

fun getApi(): Api {
    return RealApi()
}