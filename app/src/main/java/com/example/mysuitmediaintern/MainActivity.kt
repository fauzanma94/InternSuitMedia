package com.example.mysuitmediaintern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysuitmediaintern.databinding.ActivityMainBinding
import com.example.mysuitmediaintern.databinding.FragmentFirstScreenBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val _binding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}