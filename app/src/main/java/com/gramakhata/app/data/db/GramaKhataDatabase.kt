package com.gramakhata.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gramakhata.app.data.model.Customer
import com.gramakhata.app.data.model.Transaction
import com.gramakhata.app.data.model.User

@Database(
    entities = [Customer::class, Transaction::class, User::class],
    version = 2,
    exportSchema = false
)
abstract class GramaKhataDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun transactionDao(): TransactionDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: GramaKhataDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, `role` TEXT NOT NULL DEFAULT 'USER')")
            }
        }

        fun getDatabase(context: Context): GramaKhataDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GramaKhataDatabase::class.java,
                    "gramakhata_database"
                )
                .addMigrations(MIGRATION_1_2)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
