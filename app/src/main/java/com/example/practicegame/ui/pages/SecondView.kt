package com.example.practicegame.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicegame.FallingObject
import com.example.practicegame.R
import com.example.practicegame.util.getMetrics
import com.example.practicegame.util.goLeft
import com.example.practicegame.util.goRight
import kotlinx.coroutines.delay

@SuppressLint("ResourceAsColor")
@Composable
fun SecondView(
    modifier: Modifier = Modifier,
    height: Int = getMetrics(LocalContext.current).heightPixels,
    width: Int = getMetrics(LocalContext.current).widthPixels / 2 - 60,
    goToLoseScreen:(score:Int) -> Unit
) {
    var fallingObjects by remember {
        mutableStateOf(
            listOf(
                FallingObject(x = 0, y = 0, speed = 30, id = 0),
                FallingObject(x = width - 110, y = 0, speed = 30, id = 1),
                FallingObject(x = 1000, y = 0, speed = 30, id = 2)
            )
        )
    }
    var playerPosition by remember { mutableStateOf(IntOffset(width, 2000)) }
    var idCar by remember { mutableIntStateOf((0..2).random()) }
    var gameLoop by remember { mutableStateOf(true) }
    var score by remember { mutableIntStateOf(0) }
    val policeCar = ImageBitmap.imageResource(id = R.drawable.police_car)
    val car = ImageBitmap.imageResource(id = R.drawable.car)

    LaunchedEffect(gameLoop) {
        while (gameLoop) {
            delay(16L)
            fallingObjects = fallingObjects.map { obj ->
                if (obj.y >= (playerPosition.y + 103.dp.value)) {
                    idCar = (0..2).random()
                    obj.copy(y = 0)
                } else {
                    obj.copy(y = obj.y + obj.speed)
                }
            }
            fallingObjects.forEach { obj ->
                if (obj.y == 1650 && obj.x + 110 == playerPosition.x && obj.id != idCar) {
                    gameLoop = false
                    goToLoseScreen(score)
                }
            }
            if (fallingObjects[0].y >= (playerPosition.y + 103.dp.value)) {
                score = score.inc()
            }

        }
    }

    Column(
        modifier
            .fillMaxSize()
            .background(color = Color(R.color.background_color))
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(colorResource(id = R.color.score_color))
        ) {
            Text(
                text = "Score: $score",
                color = Color.White,
                fontSize = 30.sp
            )
            Text(
                text = "Time", color = Color.White,
                fontSize = 30.sp,
                modifier = modifier.align(
                    Alignment.TopEnd
                )
            )
        }


        Box(modifier.fillMaxSize()) {
            Canvas(
                modifier = modifier
                    .fillMaxSize()

            ) {
                fallingObjects.forEach { obj ->
                    if (obj.id != idCar) {
                        drawImage(
                            image = policeCar,
                            dstSize = IntSize(119.dp.toPx().toInt(), 119.dp.toPx().toInt()),
                            dstOffset = IntOffset(obj.x, obj.y),
                        )
                    }
                }
                drawImage(
                    image = car,
                    dstOffset = playerPosition,
                    dstSize = IntSize(50.dp.toPx().toInt(), 103.dp.toPx().toInt()),

                    )

            }

            Image(painter = painterResource(id = R.drawable.right_button),
                contentDescription = null,
                modifier = modifier
                    .padding(start = 295.dp, top = 644.dp)
                    .size(90.dp, 90.dp)
                    .clickable { playerPosition = goRight(playerPosition, width) })

            Image(painter = painterResource(id = R.drawable.left_button),
                contentDescription = null,
                modifier = modifier
                    .padding(start = 25.dp, top = 644.dp)
                    .size(90.dp, 90.dp)
                    .clickable { playerPosition = goLeft(playerPosition, width) })


        }
    }

}

@Composable
@Preview
fun PreviewSecondView() {
    SecondView(goToLoseScreen = {})
}