package com.codewithmandyal.phonestatusmonitorapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val event : String,
    val time : Long
)
