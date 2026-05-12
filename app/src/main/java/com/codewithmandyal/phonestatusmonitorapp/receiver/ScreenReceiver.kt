package com.codewithmandyal.phonestatusmonitorapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.codewithmandyal.phonestatusmonitorapp.repository.EventRepository

class ScreenReceiver(
    private val onScreenStateChanged: (String) -> Unit
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                Intent.ACTION_SCREEN_ON ->{
                    onScreenStateChanged("Screen ON")
                }

                Intent.ACTION_SCREEN_OFF ->{
                    onScreenStateChanged("Screen OFF")
                }
            }
    }
}