# Phone Status Monitor App

A modern Android Phone Status Monitor app built using Jetpack Compose, Broadcast Receivers, NetworkCallback, Room Database, Kotlin Flow, and StateFlow to monitor real-time device status changes and system events.

## Features
- Battery Percentage Monitoring
- Charger Connected / Disconnected Detection
- Battery Low Detection
- Airplane Mode ON / OFF Detection
- Screen ON / OFF Detection
- Bluetooth ON / OFF Monitoring
- Internet Connectivity Monitoring
- Real-time Event Logging
- Room Database Integration
- Reactive UI using StateFlow
- Jetpack Compose UI

## Tech Stack
- Kotlin
- Jetpack Compose
- BroadcastReceiver
- ConnectivityManager.NetworkCallback
- Room Database
- Kotlin Flow
- StateFlow
- MVVM Architecture

## Project Architecture

BroadcastReceiver / NetworkCallback
↓
ViewModel
↓
Room Database
↓
StateFlow
↓
Compose UI

## Learning Goals
This project was built to understand:
- Android system broadcasts
- Dynamic Broadcast Receivers
- Modern network monitoring APIs
- Reactive UI updates
- Real-time data handling
- Room + Flow integration
- Modern Android architecture patterns

## Future Improvements
- Boot Completed Receiver
- Power Saving Mode Detection
- Bluetooth Device Connection Monitoring
- Notification Support
- Charts & Analytics
- Foreground Service Integration
- Export Logs Feature

