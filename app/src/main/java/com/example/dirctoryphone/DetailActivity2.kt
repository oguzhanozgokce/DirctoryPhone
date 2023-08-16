package com.example.dirctoryphone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dirctoryphone.databinding.ActivityDetail2Binding

class DetailActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityDetail2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val nameSurname = intent.getStringExtra("putNameSurname")
        val age = intent.getStringExtra("putAge")
        val yourself = intent.getStringExtra("putYourself")

        binding.detailNameSurname.text = nameSurname.toString()
        binding.detailAge.text = age.toString()
        binding.detailYourself.text = yourself.toString()
    }
}