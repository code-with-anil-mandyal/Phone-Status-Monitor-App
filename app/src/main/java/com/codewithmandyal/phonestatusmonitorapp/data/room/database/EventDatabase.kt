package com.codewithmandyal.phonestatusmonitorapp.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithmandyal.phonestatusmonitorapp.data.room.dao.EventsDao
import com.codewithmandyal.phonestatusmonitorapp.data.room.entity.EventsEntity

@Database(
    entities = [EventsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventsDao
}