package com.example.mobilab_test_assignment.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.api.MockApi
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ItemModel


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


        button.setOnClickListener {
            getApi().deleteItem(item.id)
            remove(item)
            notifyDataSetChanged()
        }

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            getApi().changeItemStatus(item.id, isChecked)
            insert(item.copy(checkedState = isChecked), position)
            remove(item)
            notifyDataSetChanged()
        }

        return view
    }
}