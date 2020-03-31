package com.example.mobilab_test_assignment.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ItemModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ItemAddFragment manages the portion of activities that are connected to adding an item to a list,
 * pressing the back button etc
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

        view.findViewById<Button>(R.id.button_create2).setOnClickListener(
            AddItemOnClickListener(
                inputField,
                listId,
                context!!,
                findNavController(),
                bundle
            )
        )

        /**
         * Listens for click and returns to the items page
         */
        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.action_back_to_items, bundle)
        }
    }

    /**
     * Listens for click on and checks if the input in inputField is long enough
     * if everything is in order, it calls for addItem to add the item and returns to the
     * items page
     */
    class AddItemOnClickListener(
        val inputField: EditText,
        val listId: Int,
        val context: Context,
        val navController: NavController,
        val bundle: Bundle
    ) : View.OnClickListener {
        override fun onClick(v: View?) {
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
                        navController.navigate(R.id.action_back_to_items, bundle)
                    }
                })

            } else {
                inputField.setError("Item value can not be so short!")
            }
        }

    }
}
