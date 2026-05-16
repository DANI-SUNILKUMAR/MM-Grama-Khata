package com.gramakhata.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gramakhata.app.data.model.User
import com.gramakhata.app.data.prefs.SessionManager
import com.gramakhata.app.data.repository.KhataRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = KhataRepository(application)
    private val sessionManager = SessionManager(application)

    private val _authStatus = MutableLiveData<AuthResult>()
    val authStatus: LiveData<AuthResult> = _authStatus

    fun login(username: String, password: String) = viewModelScope.launch {
        val user = repository.getUserByUsername(username)
        if (user != null && user.password == password) {
            sessionManager.saveSession(user.id, user.username, user.role)
            _authStatus.value = AuthResult.Success(user)
        } else {
            _authStatus.value = AuthResult.Error("Invalid username or password")
        }
    }

    fun register(username: String, password: String, isAdmin: Boolean) = viewModelScope.launch {
        val existingUser = repository.getUserByUsername(username)
        if (existingUser != null) {
            _authStatus.value = AuthResult.Error("Username already exists")
            return@launch
        }

        val role = if (isAdmin) "ADMIN" else "USER"
        val newUser = User(username = username, password = password, role = role)
        val id = repository.registerUser(newUser)
        if (id > 0) {
            _authStatus.value = AuthResult.Success(newUser.copy(id = id))
        } else {
            _authStatus.value = AuthResult.Error("Registration failed")
        }
    }

    sealed class AuthResult {
        data class Success(val user: User) : AuthResult()
        data class Error(val message: String) : AuthResult()
    }
}
