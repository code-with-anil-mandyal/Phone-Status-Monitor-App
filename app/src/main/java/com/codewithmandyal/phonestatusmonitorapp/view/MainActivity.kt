package com.codewithmandyal.phonestatusmonitorapp.view

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codewithmandyal.phonestatusmonitorapp.data.room.DatabaseProvider
import com.codewithmandyal.phonestatusmonitorapp.receiver.AirplaneModeReceiver
import com.codewithmandyal.phonestatusmonitorapp.receiver.BatteryReceiver
import com.codewithmandyal.phonestatusmonitorapp.receiver.BluetoothReceiver
import com.codewithmandyal.phonestatusmonitorapp.receiver.InternetMonitor
import com.codewithmandyal.phonestatusmonitorapp.receiver.ScreenReceiver
import com.codewithmandyal.phonestatusmonitorapp.repository.EventRepository
import com.codewithmandyal.phonestatusmonitorapp.view.eventsScreen.EventViewModelFactory
import com.codewithmandyal.phonestatusmonitorapp.view.eventsScreen.EventsScreen
import com.codewithmandyal.phonestatusmonitorapp.view.eventsScreen.EventsViewModel
import com.codewithmandyal.phonestatusmonitorapp.view.ui.theme.PhoneStatusMonitorAppTheme

class MainActivity : ComponentActivity() {

    lateinit var batteryReceiver: BatteryReceiver
    lateinit var airplaneModeReceiver: AirplaneModeReceiver
    lateinit var screenReceiver: ScreenReceiver
    lateinit var bluetoothReceiver: BluetoothReceiver
    lateinit var internetMonitor: InternetMonitor
    private val viewModel: EventsViewModel by viewModels {

        EventViewModelFactory(
            EventRepository(
                DatabaseProvider
                    .getDatabase(applicationContext)
                    .eventDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        registerBatteryReceiver()
        registerAirPlaneModeReceiver()
        registerScreenReceiver()
        registerBluetoothReceiver()
        registerInternetManager()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//
//            bluetoothPermissionLauncher.launch(
//                Manifest.permission.BLUETOOTH_CONNECT
//            )
//        }else{
//            registerBluetoothReceiver()
//        }


        setContent {
            PhoneStatusMonitorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EventsScreen(modifier = Modifier.padding(innerPadding).padding(20.dp), viewModel)
                }
            }
        }
    }

    private fun registerInternetManager(){
        val connectivityManager =
            getSystemService(
                ConnectivityManager::class.java
            )

        internetMonitor = InternetMonitor(
            connectivityManager
        ) { isConnected ->

            viewModel.insertEvent(

                if (isConnected)
                    "Internet Connected"
                else
                    "Internet Disconnected"
            )
        }

        internetMonitor.register()
    }
   private fun registerBluetoothReceiver(){
        bluetoothReceiver = BluetoothReceiver { state ->
            viewModel.insertEvent(state)
        }

        registerReceiver(
            bluetoothReceiver,
            IntentFilter(
                BluetoothAdapter.ACTION_STATE_CHANGED
            )
        )
    }



    private fun registerScreenReceiver(){
        screenReceiver = ScreenReceiver { status ->
            viewModel.insertEvent(status)
        }

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        registerReceiver(
            screenReceiver,
            intentFilter)
    }

    private fun registerAirPlaneModeReceiver(){
        airplaneModeReceiver = AirplaneModeReceiver { isEnabled ->
            if (isEnabled) {
                viewModel.insertEvent(
                    "Airplane Mode Enabled"
                )
            } else {
                viewModel.insertEvent(
                    "Airplane Mode Disabled"
                )
            }
        }

        registerReceiver(
            airplaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }

    private fun registerBatteryReceiver(){


        batteryReceiver = BatteryReceiver(

            onBatteryChanged = { percentage ->

                viewModel.updateBatteryPercentage(percentage)

            },
            onChargerConnected = {
                viewModel.insertEvent(
                    "Charger Connected"
                )
            },

            onChargerDisconnected = {
                viewModel.insertEvent(
                    "Charger Disconnected"
                )
            },
            onBatteryFull = {
                viewModel.insertEvent(
                    "Battery Full"
                )
            },

            onBatteryLow = {
                viewModel.insertEvent(
                    "Battery Low"
                )
            }
        )


        val intent = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_BATTERY_LOW)
        }

        registerReceiver(
            batteryReceiver,
            intent
        )

    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
        unregisterReceiver(airplaneModeReceiver)
        unregisterReceiver(screenReceiver)
        unregisterReceiver(bluetoothReceiver)
        internetMonitor.unregister()
    }

//    private val bluetoothPermissionLauncher =
//        registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//            if(isGranted){
//                registerBluetoothReceiver()
//            }
//
//        }
}

