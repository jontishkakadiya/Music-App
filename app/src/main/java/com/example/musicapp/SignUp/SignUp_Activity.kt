package com.example.musicapp.SignUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.databinding.ActivitySignUpBinding

class SignUp_Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}