package com.example.musicapp.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.databinding.ActivityHomepageBinding


class HomePage_Activity : AppCompatActivity() {

    lateinit var binding: ActivityHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener{
            val intent = Intent(this, SignUp_Activity::class.java)
            startActivity(intent)
        }

        binding.btnSignin.setOnClickListener{
            val intent =Intent(this,SignIn_Activity::class.java)
            startActivity(intent)
        }

    }
}