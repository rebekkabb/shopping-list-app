package com.example.mobilab_test_assignment.api

import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel

object MockApi : Api {
    private val lists: MutableMap<Int, ListModel> = hashMapOf(1 to ListModel(1, "Test"))
    private val items: MutableMap<Int, ItemModel> = HashMap()

    private var latestListId: Int = 1
    private var latestItemId: Int = 1

    override fun getLists(): List<ListModel> {
        return lists.values.toList()
    }

    override fun getItems(listId: Int): List<ItemModel> {
        return items.values.toList()
    }

    override fun addList(listModel: ListModel): ListModel {
        val modelWithId = listModel.copy(id = latestListId)
        lists[modelWithId.id] = modelWithId
        latestListId++
        return modelWithId
    }

    override fun addItem(itemModel: ItemModel): ItemModel {
        val modelWithId = itemModel.copy(id = latestListId)
        items[modelWithId.id] = modelWithId
        latestItemId++
        return modelWithId
    }

    override fun deleteItem(itemId: Int) {
        items.remove(itemId)
    }

    override fun deleteList(listId: Int) {
        lists.remove(listId)
    }

    override fun changeItemStatus(itemId: Int, itemStatus: Boolean) {
        val itemModel = items[itemId]
        val modelWithChange = itemModel!!.copy(checkedState = itemStatus)
        items[itemId] = modelWithChange
    }
}