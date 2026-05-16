package com.gramakhata.app.ui

<<<<<<< HEAD
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gramakhata.app.databinding.ActivityRegisterBinding
import com.gramakhata.app.viewmodel.AuthViewModel
=======
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gramakhata.app.databinding.ActivityRegisterBinding
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
<<<<<<< HEAD
    private val viewModel: AuthViewModel by viewModels()
=======
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val isAdmin = binding.cbIsAdmin.isChecked

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.register(username, password, isAdmin)
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun setupObservers() {
        viewModel.authStatus.observe(this) { result ->
            when (result) {
                is AuthViewModel.AuthResult.Success -> {
                    Toast.makeText(this, "Registration successful. Please login.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is AuthViewModel.AuthResult.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
=======
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val shopName = binding.etShopName.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirm  = binding.etConfirmPassword.text.toString().trim()

            if (username.isEmpty()) { binding.etUsername.error = "Enter username"; return@setOnClickListener }
            if (shopName.isEmpty()) { binding.etShopName.error = "Enter shop name"; return@setOnClickListener }
            if (password.isEmpty()) { binding.etPassword.error = "Enter password"; return@setOnClickListener }
            if (password.length < 4) { binding.etPassword.error = "Min 4 characters"; return@setOnClickListener }
            if (password != confirm) { binding.etConfirmPassword.error = "Passwords do not match"; return@setOnClickListener }

            val prefs = getSharedPreferences("gramakhata_prefs", Context.MODE_PRIVATE)
            prefs.edit()
                .putString("username", username)
                .putString("password", password)
                .putString("shop_name", shopName)
                .putBoolean("is_logged_in", true)
                .apply()

            Toast.makeText(this, "Account created! Welcome, $shopName 🎉", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }

        binding.tvGoToLogin.setOnClickListener { finish() }
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
    }
}
