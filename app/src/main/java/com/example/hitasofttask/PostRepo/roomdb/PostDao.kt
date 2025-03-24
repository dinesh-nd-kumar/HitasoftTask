package com.example.hitasofttask.PostRepo.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hitasofttask.model.User

@Dao
interface PostDao {
    @Query("SELECT * FROM user")
    fun getAllPosts(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(posts: List<User>)

    @Delete
    suspend fun deletePost(post: User)

    @Update
    suspend fun updatePost(post: User)


}