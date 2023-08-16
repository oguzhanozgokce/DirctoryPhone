package com.example.dirctoryphone

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dirctoryphone.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSave.setOnClickListener {
            val editTextNameSurname = binding.editTextNameSurname.text.toString().trim()
            val editTextAge = binding.editTextAge.text.toString().trim()
            val editTextYourself = binding.editTextYourself.text.toString().trim()

            if (TextUtils.isEmpty(editTextNameSurname)) {
                binding.editTextNameSurname.error = "Please do not leave blank"
            }
            if (TextUtils.isEmpty(editTextNameSurname)) {
                binding.editTextAge.error = "Please do not leave blank"
            }
            if (TextUtils.isEmpty(editTextNameSurname)) {
                binding.editTextYourself.error = "Please do not leave blank"
            } else {
                val database = FirebaseDatabase.getInstance()
                val databaseReference = database.reference.child("Users")
                val id = databaseReference.push()
                id.child("id").setValue(id.key.toString())
                id.child("nameSurname").setValue(editTextNameSurname)
                id.child("age").setValue(editTextAge)
                id.child("yourself").setValue(editTextYourself)
                Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonView.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}