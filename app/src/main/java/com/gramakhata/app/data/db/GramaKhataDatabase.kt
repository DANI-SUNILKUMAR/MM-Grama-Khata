package com.gramakhata.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
<<<<<<< HEAD
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gramakhata.app.data.model.Customer
import com.gramakhata.app.data.model.Transaction
import com.gramakhata.app.data.model.User

@Database(
    entities = [Customer::class, Transaction::class, User::class],
    version = 2,
=======
import com.gramakhata.app.data.model.Customer
import com.gramakhata.app.data.model.Transaction

@Database(
    entities = [Customer::class, Transaction::class],
    version = 1,
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
    exportSchema = false
)
abstract class GramaKhataDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun transactionDao(): TransactionDao
<<<<<<< HEAD
    abstract fun userDao(): UserDao
=======
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172

    companion object {
        @Volatile
        private var INSTANCE: GramaKhataDatabase? = null

<<<<<<< HEAD
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, `role` TEXT NOT NULL DEFAULT 'USER')")
            }
        }

=======
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
        fun getDatabase(context: Context): GramaKhataDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GramaKhataDatabase::class.java,
                    "gramakhata_database"
<<<<<<< HEAD
                )
                .addMigrations(MIGRATION_1_2)
                .build()
=======
                ).build()
>>>>>>> 6d6059d4c566d92656c347fe9ee67b85fe7d8172
                INSTANCE = instance
                instance
            }
        }
    }
}
