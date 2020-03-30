package com.example.mobilab_test_assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ListModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputField = view.findViewById<EditText>(R.id.inputTitle)

        view.findViewById<Button>(R.id.button_create).setOnClickListener {
            if (inputField.text.toString().trim().length > 1) {
                val inputTitle = inputField.text.toString().trim()
                getApi().addList(ListModel(0, inputTitle))
                findNavController().navigate(R.id.action_back_to_lists)
            } else {
                inputField.setError("List name can no be empty!")
            }

        }

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.action_back_to_lists)
        }
    }
}
