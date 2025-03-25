package com.ekachandra.minifleettracker.di

import com.ekachandra.minifleettracker.core.data.TripLogDatabase
import com.ekachandra.minifleettracker.core.data.TripLogRepository
import com.ekachandra.minifleettracker.main.presentation.models.SensorViewModel
import com.ekachandra.minifleettracker.main.presentation.models.TripLogViewModel
import com.ekachandra.minifleettracker.main.presentation.models.VehicleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { TripLogDatabase.getDatabase(get()).tripLogDao() }
    single { TripLogRepository(get()) }
    viewModel { VehicleViewModel(get()) }
    viewModel { SensorViewModel() }
    viewModel { TripLogViewModel(get()) }
}