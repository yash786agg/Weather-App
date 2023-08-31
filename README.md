<h1 align="center">Weather</h1>

<p align="center">  
This application is designed to provide real-time and forecasted weather information based on the user's location.<br>
It utilizes background polling through Work Manager, updating weather data every 15 minutes, and then storing this data locally using Room Database.
</p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=30"><img alt="API" src="https://img.shields.io/badge/API-30%2B-brightgreen.svg?style=flat"/></a>
</p>

## Download
Go to the [Demo APK](https://https://github.com/yash786agg/Weather-App/tree/master/demo_apk) to download the latest APK.

<img src="/screenshots/Weather-App-demo.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries
- Minimum SDK level 30
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android#kotlin) for dependency injection.
    - Hilt Data Binding - Implementing a binding adapter using the Android Data Binding Library.
- JetPack
    - LiveData - notify domain layer data to views.
    - Flow - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
- Architecture
    - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi) - Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) - An MVVM architecture.
- [Work Manager](https://developer.android.com/guide/background/persistent/getting-started) - It s a background processing library which is used to execute background tasks which should run in a guaranteed way but not necessarily immediately..
- [Room](https://developer.android.com/training/data-storage/room) - Save data in a local database.


Above Features are used to make code simple, generic, understandable, clean and easily maintainable
for future development.

## Running and Building the application

You can run the app on a real device or an emulator.

* __[Run on a real device](https://developer.android.com/training/basics/firstapp/running-app#RealDevice)__
* __[Run on an emulator](https://developer.android.com/training/basics/firstapp/running-app#Emulator)__

# Prerequisites
* __Android Studio Flamingo | 2022.2.1__
* __Kotlin version 1.8.0__
* __Android Device with USB Debugging Enabled__