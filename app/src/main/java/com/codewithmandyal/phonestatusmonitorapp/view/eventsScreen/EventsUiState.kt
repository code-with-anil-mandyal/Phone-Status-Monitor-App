package com.codewithmandyal.phonestatusmonitorapp.view.eventsScreen

import com.codewithmandyal.phonestatusmonitorapp.data.room.entity.EventsEntity

data class EventsUiState(
    val batteryPercentage: Int =0,
    val events: List<EventsEntity> = emptyList()

)
