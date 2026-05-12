package com.codewithmandyal.phonestatusmonitorapp.repository

import com.codewithmandyal.phonestatusmonitorapp.data.room.dao.EventsDao
import com.codewithmandyal.phonestatusmonitorapp.data.room.entity.EventsEntity

class EventRepository(
    private val dao: EventsDao
) {
    suspend fun insert(eventsEntity: EventsEntity){
        dao.insertEvent(eventsEntity)
    }

    fun getAllEvents() = dao.getAllEvents()
}