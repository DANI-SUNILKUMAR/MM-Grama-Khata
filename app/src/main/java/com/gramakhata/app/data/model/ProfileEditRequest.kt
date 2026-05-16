package com.gramakhata.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_edit_requests")
data class ProfileEditRequest(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val newUsername: String,
    val newPassword: String,
    val status: String = "PENDING", // PENDING, APPROVED, REJECTED
    val requestedAt: Long = System.currentTimeMillis()
)
