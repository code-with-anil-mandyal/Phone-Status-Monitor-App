package com.codewithmandyal.phonestatusmonitorapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

class BatteryReceiver(
    private val onBatteryChanged: (Int) -> Unit,
    private val onChargerConnected: () -> Unit,
    private val onChargerDisconnected: () -> Unit,
    private val onBatteryFull: () -> Unit,
    private val onBatteryLow: () -> Unit
) : BroadcastReceiver() {

    private var isBatteryFullSent = false

    override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action){
                Intent.ACTION_BATTERY_CHANGED ->{
                    val level  = intent.getIntExtra(
                        BatteryManager.EXTRA_LEVEL,
                        -1
                    )

                    val scale = intent.getIntExtra(
                        BatteryManager.EXTRA_SCALE,
                        -1
                    )

                    val status = intent.getIntExtra(
                        BatteryManager.EXTRA_STATUS,
                        -1
                    )

                    val batteryPercentage = (level*100)/scale

                    onBatteryChanged(
                        batteryPercentage
                    )

                    val isBatteryFull = status == BatteryManager.BATTERY_STATUS_FULL

                    if(isBatteryFull && !isBatteryFullSent){
                        isBatteryFullSent = true
                        onBatteryFull()
                    }

                    if(!isBatteryFull){
                        isBatteryFullSent = false
                    }

                }

                Intent.ACTION_POWER_CONNECTED ->{
                    onChargerConnected()
                }

                Intent.ACTION_POWER_DISCONNECTED ->{
                    onChargerDisconnected()
                }

                Intent.ACTION_BATTERY_LOW ->{
                    onBatteryLow()
                }
            }
    }
}