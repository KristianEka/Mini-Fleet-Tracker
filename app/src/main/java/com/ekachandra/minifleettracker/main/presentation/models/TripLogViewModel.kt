package com.ekachandra.minifleettracker.main.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekachandra.minifleettracker.core.data.TripLogRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class TripLogViewModel(tripLogRepository: TripLogRepository) : ViewModel() {
    val tripLogs = tripLogRepository.getAllLogs()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )
}
