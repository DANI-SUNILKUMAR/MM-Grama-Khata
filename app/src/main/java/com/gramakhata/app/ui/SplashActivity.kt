package com.gramakhata.app.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import com.gramakhata.app.data.prefs.SessionManager
=======
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
import com.gramakhata.app.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
        val sessionManager = SessionManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (sessionManager.isLoggedIn()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
=======
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
            finish()
        }, 2000)
    }
}
