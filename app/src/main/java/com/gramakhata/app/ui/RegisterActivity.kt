package com.gramakhata.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gramakhata.app.databinding.ActivityRegisterBinding
import com.gramakhata.app.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}
