package com.example.mobilab_test_assignment.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.mobilab_test_assignment.R
import com.example.mobilab_test_assignment.api.getApi
import com.example.mobilab_test_assignment.model.ListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ListAddFragment manages the portion of activities that are connected to adding a list,
 * pressing the back button etc
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

        view.findViewById<Button>(R.id.button_create).setOnClickListener(
            AddListOnClickListener(
                inputField,
                context!!,
                findNavController()
            )
        )

        /**
         * Listens for click and returns to the lists page
         */
        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.action_back_to_lists)
        }
    }

    /**
     * Listens for click on and checks if the input in inputField is long enough
     * if everything is in order, it calls for addList to add the list and returns to the lists
     * page
     */
    class AddListOnClickListener(
        val inputField: EditText,
        val context: Context,
        val navController: NavController
    ) : View.OnClickListener {
        override fun onClick(v: View?) {
            if (inputField.text.toString().trim().length > 1) {
                val inputTitle = inputField.text.toString().trim()

                getApi().addList(ListModel(0, inputTitle), object : Callback<ListModel> {
                    override fun onFailure(call: Call<ListModel>?, t: Throwable) {
                        Toast.makeText(
                            context, "Problem occurred while adding the new list",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<ListModel>?,
                        response: Response<ListModel>
                    ) {
                        navController.navigate(R.id.action_back_to_lists)
                    }
                })

            } else {
                inputField.setError("List name can not be so short!")
            }
        }

    }
}
