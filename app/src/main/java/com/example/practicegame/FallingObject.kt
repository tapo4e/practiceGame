package com.example.practicegame

data class FallingObject(
    val x: Float, // Позиция по X
    var y: Float, // Позиция по Y
    val speed: Float, // Скорость падения
    val size: Float // Размер элемента
)
