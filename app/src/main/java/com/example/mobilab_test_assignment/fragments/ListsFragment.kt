package com.example.mobilab_test_assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.adapters.ListArrayAdapter
import com.example.mobilab_test_assignment.api.getApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.lists_fragment.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lists_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListFab.setOnClickListener { view ->
            Snackbar.make(view, "Lisame uuue listiiiii", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val lists = getApi().getLists()
        val adapter =
            ListArrayAdapter(
                context!!,
                R.layout.list_list,
                ArrayList(lists),
                findNavController()
            )
        val listView: ListView = view.findViewById(R.id.listList)
        listView.adapter = adapter
    }
}
