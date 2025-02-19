package com.example.practicegame

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SecondView(modifier: Modifier=Modifier){
        var fallingObjects by remember { mutableStateOf(emptyList<FallingObject>()) }
        var gameLoop by remember { mutableStateOf(true) }

        // Запуск игрового цикла
        LaunchedEffect(gameLoop) {
            while (gameLoop) {
                delay(16L) // ~60 кадров в секунду
                // Обновляем позиции элементов
                fallingObjects = fallingObjects.map { obj ->
                    obj.copy(y = obj.y + obj.speed)
                }.filter { obj -> obj.y < 2000f } // Удаляем элементы за пределами экрана

                // Добавляем новые элементы
                if (Random.nextInt(0, 100) < 5) { // Вероятность 5% за кадр
                    fallingObjects = fallingObjects + FallingObject(
                        x = Random.nextInt(0, 1000).toFloat(), // Случайная позиция X
                        y = 0f,
                        speed = Random.nextInt(5, 15).toFloat(), // Случайная скорость
                        size = Random.nextInt(30, 60).toFloat() // Случайный размер
                    )
                }
            }
        }

        // Отрисовка
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.first_back_image),
                    contentScale = ContentScale.FillBounds)
        ) {
            // Рисуем все элементы
            fallingObjects.forEach { obj ->
                drawCircle(
                    color = Color.Red,
                    radius = obj.size / 2,
                    center = Offset(obj.x, obj.y)
                )
            }
        }

}

@Composable
@Preview
fun PreviewSecondView(){
    SecondView()
}