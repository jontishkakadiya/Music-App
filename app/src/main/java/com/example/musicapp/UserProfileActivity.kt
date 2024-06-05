package com.example.musicapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Path
import android.util.Log
import com.example.musicapp.SignUp.SignIn_Activity
import com.example.musicapp.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


var TAG="UserProfileActivity"
class UserProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserProfileBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentuser = FirebaseAuth.getInstance().currentUser

        currentuser?.let{user->
            binding.userEmail.text=user.email


        }

        binding.btnLogout.setOnClickListener {
            logout()
            startActivity(Intent(this,SignIn_Activity::class.java))
        }
        binding.btnPlaylist.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.btnFavsong.setOnClickListener {
            startActivity(Intent(this,FavoriteActivity::class.java))
        }




        val firstLetter = currentuser?.email?.takeIf { it.isNotBlank() }?.get(0)?.toString() ?: ""
        Log.d(TAG, "First letter of username: $firstLetter")
        val bitmap = drawTextToBitmap(firstLetter)
        binding.userImg.setImageBitmap(bitmap)
    }

    fun logout()
    {
        MyExoPlayer.getInstance()?.release()
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,SignIn_Activity::class.java))
        finish()
    }

    private fun drawTextToBitmap(text: String): Bitmap {
        val width = 200
        val height = 200

        // Create a Bitmap with desired dimensions
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        // Get a canvas to draw on
        val canvas = Canvas(bitmap)

        val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint.color = Color.GRAY
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), backgroundPaint)

        // Set up Paint for drawing text
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.color = Color.WHITE // Example: Black text color
        textPaint.textSize = 80F

        val textWidth = textPaint.measureText(text)
        val textHeight = textPaint.descent() - textPaint.ascent()


        // Calculate text position (centered)
        val textX =  (width - textWidth) / 2
        val textY = (height - textHeight) / 2 - textPaint.ascent()

        // Draw the text onto the canvas
        canvas.drawText(text, textX, textY, textPaint)

        val path = Path().apply {
            addCircle(width / 2F, height / 2F, width.coerceAtMost(height) / 2F, Path.Direction.CCW)


            // Create a circular path
            val path = Path()
            val radius = width.coerceAtMost(height) / 2.toFloat()
            val centerX = width / 2.toFloat()
            val centerY = height / 2.toFloat()
            path.addCircle(centerX, centerY, radius, Path.Direction.CCW)

            // Clip the canvas to the circular path
            canvas.clipPath(path)


            return bitmap
        }
    }


}