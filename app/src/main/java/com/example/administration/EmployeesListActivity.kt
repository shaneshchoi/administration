package com.example.administration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmployeesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees_list)

        //Connect two buttons, add and remove button
        //If add button is clicked, move to add fragment
        //If remove button is clicked, move to remove fragment.
        val addBtn : Button = findViewById(R.id.addBtn)
        val removeBtn : Button = findViewById(R.id.removeBtn)
        addBtn.setOnClickListener {
            setViewsVisibility(View.GONE)
            val fragment = AddEmployeeFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        removeBtn.setOnClickListener {
            setViewsVisibility(View.GONE)
            val fragment = RemoveEmployeeFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        fetchFromDatabase()
    }

    private fun fetchFromDatabase() {
       val dao = EmployeeDatabase.getDatabase(applicationContext).empDao()
        val employeeEntitiesLiveData = dao.getAllEmployee()
        // Observe changes in the employeeEntities LiveData from the database
        employeeEntitiesLiveData.observe(this) { employeeEntities ->
            employeeEntities?.let {
                val employeeList = it.map { Employee(it.name, it.address, it.email, it.team) }
                updateRecyclerView(employeeList)
            }
        }
    }
        //Update RecyclerView function updates the view corresponding to the changes.
    private fun updateRecyclerView(employeeList: List<Employee>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerViewAdapter(employeeList)
        recyclerView.adapter = adapter
    }

    // Function to set visibility of views in EmployeesListActivity
    //Used to control visibility when entered to the fragments.
    private fun setViewsVisibility(visibility: Int) {
        val addButton: Button = findViewById(R.id.addBtn)
        val recyclerView: RecyclerView = findViewById((R.id.recyclerView))
        val removeButton: Button = findViewById(R.id.removeBtn)
        val image : ImageView = findViewById(R.id.employeeImage)

        addButton.visibility = visibility
        recyclerView.visibility = visibility
        removeButton.visibility = visibility
        image.visibility = visibility
    }
        //When pressed back, make the screen pop up.
    override fun onBackPressed() {
        val fragmentContainer = findViewById<View>(R.id.fragmentContainer)
        // Check if any fragments are currently added to the container
        if (fragmentContainer != null && supportFragmentManager.backStackEntryCount > 0) {
            // Pop the fragment from the back stack
            supportFragmentManager.popBackStack()
            // Restore the visibility of views
            setViewsVisibility(View.VISIBLE)
        } else {
            // If no fragments in the back stack, handle the back button as usual
            super.onBackPressed()
        }
    }

}