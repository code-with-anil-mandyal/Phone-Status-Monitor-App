package com.codewithmandyal.phonestatusmonitorapp.receiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BluetoothReceiver(
    private val onBluetoothStateChanged: (String) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {

        if (intent?.action ==
            BluetoothAdapter.ACTION_STATE_CHANGED
        ) {

            val state = intent.getIntExtra(
                BluetoothAdapter.EXTRA_STATE,
                BluetoothAdapter.ERROR
            )

            when(state) {

                BluetoothAdapter.STATE_ON -> {

                    onBluetoothStateChanged(
                        "Bluetooth ON"
                    )
                }

                BluetoothAdapter.STATE_OFF -> {

                    onBluetoothStateChanged(
                        "Bluetooth OFF"
                    )
                }
            }
        }
    }
}