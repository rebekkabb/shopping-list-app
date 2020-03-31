package com.example.mobilab_test_assignment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ItemModel
import kotlinx.android.synthetic.main.item_add_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ItemAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_add_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listId = arguments!!.getInt("id")
        val bundle = bundleOf("listId" to listId)
        val inputField = view.findViewById<EditText>(R.id.inputValue)

        view.findViewById<Button>(R.id.button_create2).setOnClickListener {
            if (inputField.text.toString().trim().length > 1) {
                val inputValue = inputField.text.toString().trim()

                getApi().addItem(ItemModel(0, listId, inputValue, false), object :
                    Callback<ItemModel> {
                    override fun onFailure(call: Call<ItemModel>, t: Throwable) {
                        Toast.makeText(
                            context, "Problem occurred while adding a new item",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<ItemModel>, response: Response<ItemModel>) {
                        findNavController().navigate(R.id.action_back_to_items, bundle)
                    }
                })

            } else {
                inputValue.setError("Item value can not be empty!")
            }
        }

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.action_back_to_items, bundle)
        }
    }
}
