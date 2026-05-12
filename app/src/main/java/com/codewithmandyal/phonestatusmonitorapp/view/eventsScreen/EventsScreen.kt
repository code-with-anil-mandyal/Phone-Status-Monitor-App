package com.codewithmandyal.phonestatusmonitorapp.view.eventsScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithmandyal.phonestatusmonitorapp.utils.toFormattedTime

@Composable
fun EventsScreen(
    modifier: Modifier,
    viewModel: EventsViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(uiState.events){
            EventsItem(it.event, it.time)
        }
    }
}

@Composable
fun EventsItem(event: String, time: Long) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(text = "${time.toFormattedTime()} -> ")

        Spacer(Modifier.width(2.dp))

        Text(text = event)
    }
}