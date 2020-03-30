package com.example.mobilab_test_assignment.api

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Callback
import retrofit2.Response

object MockApi : Api {
    private val lists: MutableMap<Int, ListModel> =
        hashMapOf(1 to ListModel(1, "Test"), 2 to ListModel(2, "Test2"))
    private val items: MutableMap<Int, ItemModel> =
        hashMapOf(
            1 to ItemModel(1, 1, "Juust", false),
            2 to ItemModel(2, 1, "Makrapulk", true)
        )

    private var latestListId: Int = 3
    private var latestItemId: Int = 3

    override fun getLists(callback: Callback<List<ListModel>>) {
        callback.onResponse(null, Response.success(lists.values.toList()))
    }

    override fun getList(listId: Int, callback: Callback<ListModel>) {
        callback.onResponse(null, Response.success(lists[listId]!!))
    }

    override fun getItems(listId: Int, callback: Callback<List<ItemModel>>) {
        callback.onResponse(
            null, Response.success(
                items.filter { (_, value) -> value.listId == listId }.values.toList()
            )
        )
    }

    override fun addList(listModel: ListModel, callback: Callback<ListModel>) {
        val modelWithId = listModel.copy(id = latestListId)
        lists[modelWithId.id] = modelWithId
        latestListId++
        callback.onResponse(null, Response.success(modelWithId))

    }

    override fun addItem(itemModel: ItemModel): ItemModel {
        val modelWithId = itemModel.copy(id = latestItemId)
        items[modelWithId.id] = modelWithId
        latestItemId++
        return modelWithId
    }

    override fun deleteItem(itemId: Int) {
        Log.d(TAG, "DELETE $itemId $items")
        items.remove(itemId)
    }

    override fun deleteList(listId: Int) {
        lists.remove(listId)
    }

    override fun changeItemStatus(itemId: Int, itemStatus: Boolean) {
        Log.d(TAG, "CHANGE $itemId $items")
        if (items.containsKey(itemId)) {
            val itemModel = items[itemId]
            val modelWithChange = itemModel!!.copy(checkedState = itemStatus)
            items[itemId] = modelWithChange
        } else {
            throw IllegalAccessException("The item you are trying to change does not exist")
        }
    }
}