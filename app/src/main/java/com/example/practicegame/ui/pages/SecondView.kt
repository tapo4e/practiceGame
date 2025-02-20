package com.example.practicegame.ui.pages

import android.util.Log
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.example.practicegame.FallingObject
import com.example.practicegame.R
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SecondView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics

    // Width and height of screen
    val width = displayMetrics.widthPixels/2
    val height = displayMetrics.heightPixels
    // Состояние для трёх падающих объектов
    var fallingObjects by remember {
        mutableStateOf(
            listOf(
                FallingObject(x = 0f, y = 0f, speed = 30f, size = 100f),
                FallingObject(x = width.toFloat(), y = 0f, speed = 30f, size = 100f),
                FallingObject(x = 1000f, y = 0f, speed = 30f, size = 100f)
            )
        )
    }
    var playerPosition by remember { mutableStateOf(Offset(width.toFloat() / 2, height.toFloat() - 200)) }

    // Состояние игрового цикла
    var gameLoop by remember { mutableStateOf(true) }

    // Загрузка изображения
    val policeCar = ImageBitmap.imageResource(id = R.drawable.police_car)
    val car = ImageBitmap.imageResource(id = R.drawable.car)

    // Запуск игрового цикла
    LaunchedEffect(gameLoop) {
        while (gameLoop) {
            delay(16L) // ~60 кадров в секунду

            // Обновляем позиции машинок
            fallingObjects = fallingObjects.map { obj ->
                if (obj.y >= height) {
                    // Если объект ушёл за пределы экрана, удаляем его
                    obj.copy(y = 0f)
                } else {
                    // Иначе продолжаем двигать объект вниз
                    obj.copy(y = obj.y + obj.speed)
                }
            }

            // Добавляем новые машинки случайным образом


            // Проверка столкновений
            fallingObjects.forEach { obj ->
                if (obj.y >= playerPosition.y - 100 && obj.x in (playerPosition.x - 50)..(playerPosition.x + 50)) {
                    // Если произошло столкновение, завершаем игру
                    gameLoop = false
                    Log.d("asd","fal")
                }
            }
        }
    }


    // Отрисовка
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color(R.color.background_color))
        ) {
            // Рисуем все элементы
            fallingObjects.forEach {obj->
               drawImage( image = policeCar,
                   dstSize = IntSize(500,500),
                   dstOffset = IntOffset(obj.x.toInt(),obj.y.toInt()),
               )
            }
            drawImage( image = car,
                dstOffset = IntOffset(width/2,2000),
                dstSize = IntSize(250,500),

            )

        }

}

@Composable
@Preview
fun PreviewSecondView(){
    SecondView()
}