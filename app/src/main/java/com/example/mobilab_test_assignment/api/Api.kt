package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Callback

interface Api {
    fun getLists(callback: Callback<List<ListModel>>)
    fun getList(listId: Int): ListModel
    fun getItems(listId: Int): List<ItemModel>
    fun addList(listModel: ListModel): ListModel
    fun addItem(itemModel: ItemModel): ItemModel
    fun deleteItem(itemId: Int)
    fun deleteList(listId: Int)
    fun changeItemStatus(itemId: Int, itemStatus: Boolean)
}

fun getApi(): Api {
    return RealApi()
}