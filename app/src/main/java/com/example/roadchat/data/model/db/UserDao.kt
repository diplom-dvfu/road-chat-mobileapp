package com.example.roadchat.data.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY userId ASC limit 1")
    fun getUserInfo(): Flow<User>

    @Query("SELECT * FROM user ORDER BY userId ASC")
    fun getUserAccounts(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

}