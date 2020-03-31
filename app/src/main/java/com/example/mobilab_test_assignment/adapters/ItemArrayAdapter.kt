package com.example.mobilab_test_assignment.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ItemModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ItemArrayAdapter manages the portion of activities that are connected to displaying the items in
 * a column, more specifically displaying each items data, modifying it and deleting it
 */
class ItemArrayAdapter(
    context: Context,
    resource: Int,
    objects: List<ItemModel>
) : ArrayAdapter<ItemModel>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = (context as Activity).layoutInflater
        val view: View
        view = convertView ?: inflater.inflate(R.layout.item_list, parent, false)

        val item = getItem(position)
        val text = view.findViewById<TextView>(R.id.textView)
        text.text = (item!!.value)

        val checkbox = view.findViewById<CheckBox>(R.id.itemCheckbox)
        checkbox.isChecked = item.checkedState

        val button = view.findViewById<ImageButton>(R.id.imageButton)


        /**
         * This listens for click in one of the items rows and deletes the item whose row button
         * was clicked then updates the page
         */
        button.setOnClickListener {
            getApi().deleteItem(item.id, object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(
                        context, "Problem occurred while deleting the item",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    remove(item)
                    notifyDataSetChanged()
                }
            })
        }

        /**
         * This listens for click in one of the items rows and checks or unchecks the checkbox in
         * that row accordingly then updates the page
         */
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            try {
                getApi().changeItemStatus(item.id, isChecked, object : Callback<Unit> {
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Toast.makeText(
                            context, "Problem occurred while deleting the list",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        insert(item.copy(checkedState = isChecked), position)
                        remove(item)
                        notifyDataSetChanged()
                    }
                })
            } catch (e: IllegalAccessException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }
        }

        return view
    }
}