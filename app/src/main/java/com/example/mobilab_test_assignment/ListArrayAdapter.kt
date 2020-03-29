package com.example.mobilab_test_assignment

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.mobilab_test_assignment.api.MockApi
import com.example.mobilab_test_assignment.model.ListModel


class ListArrayAdapter(
    context: Context,
    resource: Int,
    objects: List<ListModel>,
    private val navController: NavController
) : ArrayAdapter<ListModel>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = (context as Activity).layoutInflater
        val view: View

        view = convertView ?: inflater.inflate(R.layout.list_list, parent, false)

        val listItem = getItem(position)

        val text = view.findViewById<TextView>(R.id.textView)
        text.text = (listItem!!.name)

        val button = view.findViewById<ImageButton>(R.id.imageButton)

        view.setOnClickListener {
            val bundle = bundleOf("listId" to listItem.id)
            navController.navigate(R.id.action_move_to_items, bundle)
        }

        button.setOnClickListener {
            MockApi.deleteList(listItem.id)
            remove(listItem)
            notifyDataSetChanged()
        }

        return view
    }
}