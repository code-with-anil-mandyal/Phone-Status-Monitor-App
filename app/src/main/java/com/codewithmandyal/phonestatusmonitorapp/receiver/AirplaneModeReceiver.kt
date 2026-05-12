package com.codewithmandyal.phonestatusmonitorapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeReceiver(
    private val onAirPlaneModelChanged: (Boolean) -> Unit
): BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val isEnabled = intent.getBooleanExtra(
                "state",
                false
            )

            onAirPlaneModelChanged(isEnabled)
        }
    }

}