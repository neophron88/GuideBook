package com.neophron88.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neophron88.database.upcoming.models.UpcomingEntity
import com.neophron88.database.upcoming.roomImpl.UpcomingDao

@Database(
    version = 1,
    entities = [UpcomingEntity::class],
    exportSchema = true
)
abstract class GuideBookDatabase : RoomDatabase() {

    abstract fun getUpcomingDao(): UpcomingDao


    companion object {
        @Volatile
        private var database: GuideBookDatabase? = null

        fun getDatabase(appContext: Context): GuideBookDatabase {
            val temp1 = database
            if (temp1 != null) return temp1

            synchronized(this) {
                val temp2 = database
                if (temp2 != null) return temp2
                val temp3 = Room.databaseBuilder(
                    appContext,
                    GuideBookDatabase::class.java,
                    "guideBook_db"
                ).build()
                database = temp3
                return temp3
            }
        }
    }

}