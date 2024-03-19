package com.example.administration

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//EmployeeViewHolder that holds Image, Id, Name, Email, Address, and Team. Infact,
//Image is set by a default.
class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val employeeImage: ImageView = itemView.findViewById(R.id.employeeImage)
    val employeeId : TextView = itemView.findViewById(R.id.employeeId)
    val employeeName: TextView = itemView.findViewById(R.id.employeeName)
    val employeeEmail: TextView = itemView.findViewById(R.id.employeeEmail)
    val employeeAddress: TextView = itemView.findViewById(R.id.employeeAddress)
    val employeeTeam: TextView = itemView.findViewById(R.id.employeeTeam)
}
