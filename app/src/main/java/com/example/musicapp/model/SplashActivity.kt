package com.example.musicapp.model

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapp.SignUp.HomePage_Activity
import com.example.musicapp.databinding.ActivitySplashBinding


class Splashactivity : AppCompatActivity() {

    private  lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToMainActivityAfterDelay()

//        val scal: Animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
//        binding.llsplash.startAnimation(scal)
    }

    private fun navigateToMainActivityAfterDelay() {

        binding.splashText.postDelayed({
            startActivity(Intent(this, HomePage_Activity::class.java))
            finish()
        }, 3000)


//        binding.imgsplash.postDelayed({
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }, 3000)
    }
}