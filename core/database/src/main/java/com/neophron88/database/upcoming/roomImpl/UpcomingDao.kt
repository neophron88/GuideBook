package com.neophron88.database.upcoming.roomImpl

import androidx.room.*
import com.neophron88.database.upcoming.models.UpcomingEntity

@Dao
abstract class UpcomingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(upcomingList: List<UpcomingEntity>)

    @Transaction
    open suspend fun insertAfterDeleteAll(upcomingList: List<UpcomingEntity>) {
        clearAll()
        insertAll(upcomingList)
    }

    @Query("SELECT * FROM upcoming_table LIMIT :loadSize OFFSET :offset")
    abstract suspend fun fetchUpcomingList(loadSize: Int, offset: Int): List<UpcomingEntity>

    @Query("SELECT * FROM upcoming_table WHERE id=:upcomingId")
    abstract suspend fun fetchUpcoming(upcomingId: Long): UpcomingEntity

    @Query("DELETE FROM upcoming_table")
    abstract suspend fun clearAll()
}