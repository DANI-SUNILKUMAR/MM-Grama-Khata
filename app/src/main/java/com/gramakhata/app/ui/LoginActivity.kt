package com.gramakhata.app.ui

<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gramakhata.app.databinding.ActivityLoginBinding
import com.gramakhata.app.viewmodel.AuthViewModel
=======
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gramakhata.app.databinding.ActivityLoginBinding
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
<<<<<<< HEAD
    private val viewModel: AuthViewModel by viewModels()
=======
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
=======
        // If already logged in, skip to main
        val prefs = getSharedPreferences("gramakhata_prefs", Context.MODE_PRIVATE)
        if (prefs.getBoolean("is_logged_in", false)) {
            goToMain()
            return
        }

>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

<<<<<<< HEAD
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(username, password)
        }

        binding.tvRegister.setOnClickListener {
=======
            if (username.isEmpty()) { binding.etUsername.error = "Enter username"; return@setOnClickListener }
            if (password.isEmpty()) { binding.etPassword.error = "Enter password"; return@setOnClickListener }

            // Get stored credentials
            val savedUser = prefs.getString("username", null)
            val savedPass = prefs.getString("password", null)

            when {
                savedUser == null -> {
                    Toast.makeText(this, "No account found. Please register first.", Toast.LENGTH_SHORT).show()
                }
                username == savedUser && password == savedPass -> {
                    prefs.edit().putBoolean("is_logged_in", true).apply()
                    goToMain()
                }
                else -> {
                    Toast.makeText(this, "Incorrect username or password.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tvGoToRegister.setOnClickListener {
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

<<<<<<< HEAD
    private fun setupObservers() {
        viewModel.authStatus.observe(this) { result ->
            when (result) {
                is AuthViewModel.AuthResult.Success -> {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
                is AuthViewModel.AuthResult.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
=======
    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
    }
}
