package com.example.practicegame

import android.app.Application
import com.example.practicegame.data.Data

class App:Application() {
    val dataBase by lazy{Data(applicationContext)}
}
