package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Callback
import retrofit2.Response

/**
 * MockApi was created for easy local development, before the server and database were set up
 */
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

    override fun addItem(itemModel: ItemModel, callback: Callback<ItemModel>) {
        val modelWithId = itemModel.copy(id = latestItemId)
        items[modelWithId.id] = modelWithId
        latestItemId++
        callback.onResponse(null, Response.success(modelWithId))
    }

    override fun deleteItem(itemId: Int, callback: Callback<Unit>) {
        //Log.d(TAG, "DELETE $itemId $items")
        items.remove(itemId)
        callback.onResponse(null, Response.success(Unit))
    }

    override fun deleteList(listId: Int, callback: Callback<Unit>) {
        lists.remove(listId)
        callback.onResponse(null, Response.success(Unit))
    }

    override fun changeItemStatus(itemId: Int, itemStatus: Boolean, callback: Callback<Unit>) {
        //Log.d(TAG, "CHANGE $itemId $items")
        if (items.containsKey(itemId)) {
            val itemModel = items[itemId]
            val modelWithChange = itemModel!!.copy(checkedState = itemStatus)
            items[itemId] = modelWithChange
            callback.onResponse(null, Response.success(Unit))
        } else {
            throw IllegalAccessException("The item you are trying to change does not exist")
        }

    }
}