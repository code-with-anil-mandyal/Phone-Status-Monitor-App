package com.codewithmandyal.phonestatusmonitorapp.receiver

import android.net.ConnectivityManager
import android.net.Network

class InternetMonitor(
    private val connectivityManager: ConnectivityManager,
    private val onInternetChanged: (Boolean) -> Unit
) {
    private val callback = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            onInternetChanged(true)

        }

        override fun onLost(network: Network) {
            onInternetChanged(false)
        }
    }

    fun register(){
        connectivityManager.registerDefaultNetworkCallback(
            callback
        )
    }

    fun unregister(){
        connectivityManager.unregisterNetworkCallback(
            callback
        )
    }
}