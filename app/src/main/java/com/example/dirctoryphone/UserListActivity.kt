package com.example.dirctoryphone

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dirctoryphone.databinding.ActivityUserListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale.getDefault


class UserListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserListBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerView = binding.userList
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<User>()
        adapter = CustomAdapter(::onUserClick)
        getUserData()

        binding.buttonAddUsers.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.serachview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText?.trim()?.lowercase(getDefault())
                if (searchText.isNullOrEmpty().not()) {
                    userArrayList.filter { (it.nameSurname ?: "").contains(searchText!!) }.let {
                        adapter.updateList(it)
                        userRecyclerView.adapter = adapter
                    }
                } else {
                    adapter.updateList(userArrayList)
                    userRecyclerView.adapter = adapter
                }
                return true
            }
        })
    }

    private fun getUserData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }
                    adapter.updateList(userArrayList)
                    userRecyclerView.adapter = adapter                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun onUserClick(user: User) {
        Intent(this, DetailActivity2::class.java).apply {
            putExtra("putNameSurname", user.nameSurname)
            putExtra("putAge", user.age)
            putExtra("putYourself", user.yourself)
            startActivity(this)
        }
    }
}