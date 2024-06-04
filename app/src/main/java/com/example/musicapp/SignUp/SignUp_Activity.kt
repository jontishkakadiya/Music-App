package com.example.musicapp.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.databinding.ActivitySignUpBinding
import android.util.Patterns
import android.widget.Toast
import java.util.regex.Pattern
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.example.musicapp.UserProfileActivity
import android.util.Log
import android.view.View

var TAG="Signup_activity"
class SignUp_Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateAccount.setOnClickListener {

            val email = binding.edtEmailAddress.text.toString()
            val fullName= binding.edtFullname.text.toString()
            val username= binding.edtUsername.text.toString()
            val password= binding.edtPassword.text.toString()
            val confirmpassword= binding.edtConfirmPassword.text.toString()
            val inprogress= binding.progressbar



            if(!Pattern.matches(Patterns.PHONE.pattern(),username)){
                binding.edtUsername.error = "Length should be 12 char"
                return@setOnClickListener
            }

            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email))
            {
                binding.edtEmailAddress.error = "Invalid Email"
                return@setOnClickListener
            }

            if(password.length <6){
                binding.edtPassword.error = "Length should be 6 char"
                return@setOnClickListener
            }

            if(password != confirmpassword)
            {
                binding.edtConfirmPassword.error = "Password not matched"
                return@setOnClickListener
            }


            createAccountWithFirebase(email,password,username)

        }
        binding.GoToMainScreen.setOnClickListener {
            startActivity(Intent(this@SignUp_Activity,SignIn_Activity::class.java))
        }

    }

    private fun createAccountWithFirebase(email: String, password: String, fullName: String)
    {
        setupProgressbar(true)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {authResult->
                setupProgressbar(false)
                Toast.makeText(applicationContext,"User created sucessfully",Toast.LENGTH_LONG).show()
                finish()
            }.addOnFailureListener {
                setupProgressbar(false)
                Toast.makeText(applicationContext,"Create account failed",Toast.LENGTH_LONG).show()
            }

        intent.putExtra("Full_name",fullName)
        startActivity(Intent(this@SignUp_Activity, UserProfileActivity::class.java).apply {
            putExtra("FULL_NAME", fullName)
        })

    }

    private fun saveUserToDatabase(userId: String, username: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("users")


        databaseReference.child(userId).child("username").setValue(username)
            .addOnSuccessListener {

            }
            .addOnFailureListener {exception->
                Log.e(TAG, "Error saving username to database: ${exception.message}")
            }

    }

    private fun setupProgressbar(inProgress:Boolean)
    {
        if (inProgress)
        {
            binding.btnCreateAccount.visibility=View.GONE
            binding.progressbar.visibility= View.VISIBLE
        }
        else
        {
            binding.btnCreateAccount.visibility=View.VISIBLE
            binding.progressbar.visibility= View.GONE
        }
    }
}











//package com.example.musicapp.SignUp
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.musicapp.databinding.ActivitySignUpBinding
//import com.example.musicapp.MainActivity
//
//class SignUp_Activity : AppCompatActivity() {
//
//    private lateinit var binding: ActivitySignUpBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySignUpBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnCreateAccount.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//        binding.GoToMainScreen.setOnClickListener {
//            val intent = Intent(this, SignIn_Activity::class.java)
//            startActivity(intent)
//        }
//
//    }
//}