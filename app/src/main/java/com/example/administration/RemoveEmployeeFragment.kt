package com.example.administration

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoveEmployeeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_remove_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val IdEditText = view.findViewById<EditText>(R.id.editTextId)
        val removeEmployeeButton = view.findViewById<Button>(R.id.removeButton)

        // Set up click listener for the remove button
         removeEmployeeButton.setOnClickListener {
            val id = IdEditText.text.toString().toInt()
                 removeEmployeesFromDatabase(id)
             Toast.makeText(this.context, "Successfully removed", Toast.LENGTH_SHORT).show()
             navigateToEmployeesListActivity()
        }

    }
    //Navigate to the activity
    private fun navigateToEmployeesListActivity() {
        val intent = Intent(requireContext(), EmployeesListActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
    //Remove Employees from the database, and reorder the Ids in order from 1.
    private fun removeEmployeesFromDatabase(Id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = EmployeeDatabase.getDatabase(requireContext().applicationContext).empDao()
                    dao.deleteEmployeeById(Id)
                    dao.reorderIds()

                }
            }


}
