package com.neophron88.database.upcoming.roomImpl

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neophron88.database.upcoming.models.UpcomingEntity

interface UpcomingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UpcomingEntity>)

    @Query("SELECT * FROM upcoming_table LIMIT :loadSize OFFSET :offset")
    fun fetchUpcomingList(loadSize: Int, offset: Int): List<UpcomingEntity>

    @Query("SELECT * FROM upcoming_table WHERE id=:upcomingId")
    fun fetchUpcoming(upcomingId: Long): UpcomingEntity

    @Query("DELETE FROM upcoming_table")
    suspend fun clearAll()
}