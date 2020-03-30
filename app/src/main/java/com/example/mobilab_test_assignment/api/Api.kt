package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Callback

interface Api {
    fun getLists(callback: Callback<List<ListModel>>)
    fun getList(listId: Int, callback: Callback<ListModel>)
    fun getItems(listId: Int, callback: Callback<List<ItemModel>>)
    fun addList(listModel: ListModel, callback: Callback<ListModel>)
    fun addItem(itemModel: ItemModel): ItemModel
    fun deleteItem(itemId: Int)
    fun deleteList(listId: Int)
    fun changeItemStatus(itemId: Int, itemStatus: Boolean)
}

fun getApi(): Api {
    return RealApi()
}