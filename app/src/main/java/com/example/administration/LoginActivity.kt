package com.example.administration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    //Created users admin and non-admin.
    private val userList = listOf(
        User("admin@google.com", "password", true),
        User("normal@google.com", "password", false)
    )
    private lateinit var inputIdField: EditText
    private lateinit var inputPasswordField: EditText
    private lateinit var signInBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inputIdField = findViewById(R.id.userId)
        inputPasswordField = findViewById(R.id.userPassword)
        signInBtn = findViewById(R.id.signInBtn)
        signInBtn.setOnClickListener {
            val authenticatedUser = userList.find{ user ->
                user.username == inputIdField.text.toString() && user.password == inputPasswordField.text.toString()
            }
            //If user is admin, pass the intent boolean so that the MainActivity knows that the person
            //logged in is an admin which enables the visibility of Employee navigation.
            //If user isn't an admin, let the user log-in, but shouldn't be able to see the Employee in the nagivation.
            if (authenticatedUser != null){
                if (authenticatedUser.admin){
                    Toast.makeText(this, "Logged in as Admin", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("admin", true)
                    intent.putExtra("signIn", true)
                    startActivity(intent)}
                else {
                    Toast.makeText(this, "Logged in as non-admin", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("admin", false)
                    intent.putExtra("signIn", true)
                    startActivity(intent)
                }
                //Error prevention, if users doesn't enter the input, alert the user,
                //If user enters wrong ID or Password, alert the user.
            } else{
                if (inputIdField.text.toString().isEmpty() || inputPasswordField.text.toString().isEmpty()){
                    Toast.makeText(this,"Please fill the blank", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Failed to Login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}