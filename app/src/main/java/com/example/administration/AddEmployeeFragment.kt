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

class AddEmployeeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_employee, container, false)
    }
    /*This fragment is intend to add Employee into the database
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Set each text field connected from the xml file
        super.onViewCreated(view, savedInstanceState)
        val nameEditText = view.findViewById<EditText>(R.id.editTextName)
        val addressEditText = view.findViewById<EditText>(R.id.editTextAddress)
        val emailEditText = view.findViewById<EditText>(R.id.editTextEmail)
        val teamEditText = view.findViewById<EditText>(R.id.editTextTeam)
        val addEmployeeButton = view.findViewById<Button>(R.id.addButton)

        // Set up click listener for the add button
        addEmployeeButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val address = addressEditText.text.toString()
            val email = emailEditText.text.toString()
            val team = teamEditText.text.toString()
            //Error prevention, if any of the inputs are empty, alert user.
            if (name.isEmpty() || address.isEmpty() || email.isEmpty() || team.isEmpty()) {
                Toast.makeText(this.context, "Please fill the blank", Toast.LENGTH_SHORT).show()
            } else {
                //Add each of entered name, address, email, and team into the Employee() Object
                val employeesList = mutableListOf<Employee>(
                    Employee(name, address, email, team)
                )
                //Add the created employeeList into the database
                addEmployeesToDatabase(employeesList)
                Toast.makeText(this.context, "New employee has been added", Toast.LENGTH_SHORT)
                    .show()
                //Navigate back to the Activity
                navigateToEmployeesListActivity()
            }
        }

    }
    //This function allows to navigate back to the EmployeesListActivity
    private fun navigateToEmployeesListActivity() {
        val intent = Intent(requireContext(), EmployeesListActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
    //This function adds employees to database
    private fun addEmployeesToDatabase(employeeList: List<Employee>?) {
        employeeList?.let {
            //Using map, insert them into the database
            val employeeEntities = employeeList.map {employee ->
                EmployeeEntity(0, employee.name, employee.address, employee.email, employee.team)
            }
            CoroutineScope(Dispatchers.IO).launch {
                val dao = EmployeeDatabase.getDatabase(context?.applicationContext ?: return@launch)
                   .empDao()
                for (employeeEntity in employeeEntities) {
                    dao.insertEmployee(employeeEntity)
                }
            }
        }
    }
}
