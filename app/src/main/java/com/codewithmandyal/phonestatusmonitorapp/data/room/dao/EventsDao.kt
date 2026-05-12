package com.codewithmandyal.phonestatusmonitorapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codewithmandyal.phonestatusmonitorapp.data.room.entity.EventsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {
    @Insert
    suspend fun insertEvent(event: EventsEntity)

    @Query("SELECT * FROM events ORDER BY id DESC")
    fun getAllEvents(): Flow<List<EventsEntity>>
}