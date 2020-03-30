package com.example.mobilab_test_assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.adapters.ItemArrayAdapter
import com.example.mobilab_test_assignment.api.getApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.items_fragment.*

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

        addItemFab.setOnClickListener{ view ->
            Snackbar.make(view, "Lisame uuue itemiiiiii", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val listTitle = view.findViewById<TextView>(R.id.listTitle)
        listTitle.text = getApi().getList(listId).name

        val items = getApi().getItems(listId)
        val adapter = ItemArrayAdapter(context!!, R.layout.item_list, ArrayList(items))
        val listView: ListView = view.findViewById(R.id.itemList)
        listView.adapter = adapter

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.action_back_to_lists)
        }
    }
}
