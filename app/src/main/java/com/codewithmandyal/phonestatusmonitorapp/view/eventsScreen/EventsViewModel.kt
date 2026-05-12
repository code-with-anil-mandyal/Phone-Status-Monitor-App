package com.codewithmandyal.phonestatusmonitorapp.view.eventsScreen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.codewithmandyal.phonestatusmonitorapp.data.room.entity.EventsEntity
import com.codewithmandyal.phonestatusmonitorapp.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class EventsViewModel(
    private val repository : EventRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(EventsUiState())
    val uiState: StateFlow<EventsUiState> = _uiState

    init {
        observeEvents()
    }

   private fun observeEvents(){
        repository.getAllEvents()
            .onEach { events ->

                _uiState.value = _uiState.value.copy(
                    events = events
                )
            }.launchIn(viewModelScope)
   }

    fun updateBatteryPercentage(
        percentage: Int
    ){
        _uiState.value = _uiState.value.copy(
            batteryPercentage = percentage
        )
    }




    fun insertEvent(message: String){
        viewModelScope.launch {
            repository.insert(
                EventsEntity(
                    event = message,
                    time = System.currentTimeMillis()
                )
            )
        }
    }
}

class EventViewModelFactory(
    private val repository: EventRepository
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventsViewModel(repository) as T
    }
}