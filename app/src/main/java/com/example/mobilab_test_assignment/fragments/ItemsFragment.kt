package com.example.mobilab_test_assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.adapters.ItemArrayAdapter
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ItemModel
import com.example.mobilab_test_assignment.model.ListModel
import kotlinx.android.synthetic.main.items_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ItemsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.items_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listId = arguments!!.getInt("listId")

        var bundle = bundleOf("id" to listId)


        addItemFab.setOnClickListener { view ->
            findNavController().navigate(R.id.action_to_add_item, bundle)
        }

        val listTitle = view.findViewById<TextView>(R.id.listTitle)

        getApi().getList(listId, object : Callback<ListModel> {
            override fun onFailure(call: Call<ListModel>?, t: Throwable?) {
                Toast.makeText(
                    context, "Problem occurred while requesting the list",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<ListModel>?,
                response: Response<ListModel>
            ) {
                listTitle.text = response.body()!!.name
            }
        })

        val listView: ListView = view.findViewById(R.id.itemList)

        getApi().getItems(listId, object : Callback<List<ItemModel>> {
            override fun onFailure(call: Call<List<ItemModel>>?, t: Throwable) {
                Toast.makeText(
                    context, "Problem occurred while requesting items",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<List<ItemModel>>?,
                response: Response<List<ItemModel>>
            ) {
                val items = response.body()!!
                val adapter = ItemArrayAdapter(context!!, R.layout.item_list, ArrayList(items))
                listView.adapter = adapter
            }
        })

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.action_back_to_lists)
        }
    }
}
