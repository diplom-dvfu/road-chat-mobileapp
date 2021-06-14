package com.example.roadchat.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roadchat.data.db.dao.ChatDao
import com.example.roadchat.data.db.dao.UserDao
import com.example.roadchat.data.db.entity.Chat
import com.example.roadchat.data.db.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Chat::class, User::class], version = 7)
abstract class ChatsDatabase : RoomDatabase() {

    abstract fun ChatDao(): ChatDao
    abstract fun UserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: ChatsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ChatsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatsDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.ChatDao())
                        database.UserDao().insert(User(0, "ikhacha"))
                        database.UserDao().insert(User(1, "М111ЕХ125"))
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(chatDao: ChatDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            chatDao.deleteAll()

            chatDao.insert(Chat(0, "vixorem", "Перепаркуйте автомобиль", "31.05"))
            chatDao.insert(Chat(1, "У502НК125", "Хорошо покатались", "12.03"))
            chatDao.insert(Chat(2, "М651ЕЕ125", "Спасибо большое!", "5.02"))

        }
    }
}