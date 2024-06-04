package com.example.musicapp.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.databinding.ActivitySignInBinding
import com.example.musicapp.MainActivity
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SignIn_Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val email = binding.siginEmail.text.toString()
            val password = binding.siginPassword.text.toString()

            if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), email)) {
                binding.siginEmail.error = "Invalid Email"
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.siginPassword.error = "Length should be 6 char"
                return@setOnClickListener
            }

            loginwithFirebase(email, password)

        }

        binding.GoToSignup.setOnClickListener {
            val intent = Intent(this, SignUp_Activity::class.java)
            startActivity(intent)
        }

    }

    private fun loginwithFirebase(email: String, password: String) {
        setupProgressbar(true)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                setupProgressbar(false)
                //Toast.makeText(applicationContext,"User Login sucessfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@SignIn_Activity, MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                setupProgressbar(false)
                Toast.makeText(applicationContext, "User Login failed", Toast.LENGTH_LONG).show()
            }

    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().currentUser?.apply {
            startActivity(Intent(this@SignIn_Activity, MainActivity::class.java))
            finish()
        }
    }

    private fun setupProgressbar(inProgress: Boolean) {
        if (inProgress) {
            binding.btnLogin.visibility = View.GONE
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressbar.visibility = View.GONE
        }
    }

}


//package com.example.musicapp.SignUp
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.musicapp.databinding.ActivitySignInBinding
//import com.example.musicapp.MainActivity
//
//class SignIn_Activity : AppCompatActivity() {
//
//    private lateinit var binding: ActivitySignInBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySignInBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnLogin.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.GoToSignup.setOnClickListener {
//            val intent = Intent(this, SignUp_Activity::class.java)
//            startActivity(intent)
//        }
//
//    }
//}