package com.example.practicegame.util

import android.content.Context
import android.util.DisplayMetrics
import androidx.compose.ui.unit.IntOffset

fun getMetrics(context: Context):DisplayMetrics = context.resources.displayMetrics

fun goRight(car:IntOffset,width:Int): IntOffset {
     return when(car.x){
        110 -> car.copy(x=width)
        else -> car.copy(x=1110)
    }
}

fun goLeft(car:IntOffset, width:Int):IntOffset{
    return when(car.x){
        1110 -> car.copy(x=width)
        else -> car.copy(x=110)
    }
}