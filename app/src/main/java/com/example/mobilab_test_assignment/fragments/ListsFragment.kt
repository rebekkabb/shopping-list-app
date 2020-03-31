package com.example.mobilab_test_assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.adapters.ListArrayAdapter
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ListModel
import kotlinx.android.synthetic.main.lists_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ListsFragment manages the portion of activities that are connected to displaying lists and
 * going forward to item display page or to list creation page
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

        val listView: ListView = view.findViewById(R.id.listList)

        /**
         * As soon as the view is created we call getLists() to get all the information about the
         * lists which we want to display
         * We move into ListArrayAdapter to display them all
         */
        getApi().getLists(
            object : Callback<List<ListModel>> {
                override fun onFailure(call: Call<List<ListModel>>?, t: Throwable?) {
                    Toast.makeText(
                        context, "Problem occurred while requesting lists",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(
                    call: Call<List<ListModel>>?,
                    response: Response<List<ListModel>>?
                ) {
                    val lists = response!!.body()!!
                    val adapter =
                        ListArrayAdapter(
                            context!!,
                            R.layout.list_list,
                            ArrayList(lists),
                            findNavController()
                        )
                    listView.adapter = adapter
                }
            })

        /**
         * Listens for click and goes on to the list creation page
         */
        addListFab.setOnClickListener {
            findNavController().navigate(R.id.action_to_add_list)
        }
    }
}
