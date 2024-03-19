package com.example.administration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(private var employeeList: List<Employee>):
    RecyclerView.Adapter<EmployeeViewHolder>() {
    private var count = 1

    // Function to update the adapter's data with a new list and notify the RecyclerView
    // Create a new ViewHolder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            // Inflate the layout for a single item view
            .inflate(R.layout.list_item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    // Bind data to the views in the ViewHolder
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employeeList[position]

        // Set the RecyclerView to show which ones to represent on the screen.
        holder.employeeId.text = "ID : " + count.toString()
        count++
        holder.employeeName.text = employee.name
        holder.employeeEmail.text = employee.email
        holder.employeeTeam.text = employee.team
        holder.employeeAddress.text = employee.address
    }
    // It returns the total number of Employees being called
    override fun getItemCount(): Int {
        return employeeList.size
    }
}
