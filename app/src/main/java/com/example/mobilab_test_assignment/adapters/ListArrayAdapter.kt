package com.example.mobilab_test_assignment.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ListArrayAdapter manages the portion of activities that are connected to displaying the lists in
 * a column, more specifically displaying each lists data and deleting it
 */
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

        /**
         * This listens for click in one of the lists rows and goes to the items page of that list
         * with a bundle that contains the listId
         */
        view.setOnClickListener {
            val bundle = bundleOf("listId" to listItem.id)
            navController.navigate(R.id.action_move_to_items, bundle)
        }

        /**
         * This listens for click in one of the lists rows and deletes the list whose row button
         * was clicked then updates the page
         */
        button.setOnClickListener {
            remove(listItem)
            getApi().deleteList(listItem.id, object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(
                        context, "Problem occurred while deleting the list",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    notifyDataSetChanged()
                }
            })
        }
        return view
    }
}