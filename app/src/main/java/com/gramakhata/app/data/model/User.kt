package com.gramakhata.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val password: String, // In a real app, hash this
    val role: String = "USER" // ADMIN or USER
)
