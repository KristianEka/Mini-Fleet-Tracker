package com.ekachandra.minifleettracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ekachandra.minifleettracker.main.presentation.LiveVehicleMapScreen
import com.ekachandra.minifleettracker.ui.theme.MiniFleetTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniFleetTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LiveVehicleMapScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}