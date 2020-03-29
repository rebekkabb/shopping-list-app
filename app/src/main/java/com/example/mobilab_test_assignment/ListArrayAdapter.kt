package com.example.mobilab_test_assignment

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.mobilab_test_assignment.api.MockApi
import com.example.mobilab_test_assignment.model.ListModel
import java.math.BigDecimal


class ListArrayAdapter(
    context: Context,
    resource: Int,
    objects: List<ListModel>
) : ArrayAdapter<ListModel>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = (context as Activity).layoutInflater
        val view: View

        view = convertView ?: inflater.inflate(R.layout.list_list, parent, false)

        val listItem = getItem(position)

        val text = view.findViewById<TextView>(R.id.textView)
        text.text = (listItem!!.name)

        val button = view.findViewById<ImageButton>(R.id.imageButton)

        button.setOnClickListener {
            MockApi.deleteList(listItem.id)
            remove(listItem)
            notifyDataSetChanged()
            Log.d("rebsu", MockApi.getLists().toString());
        }

        return view
    }
}