package com.example.practicegame.ui.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChangePosButton(modifier:Modifier=Modifier){
    Box(
        modifier
            .padding(start = 3.dp)
            .drawWithCache {
                val path = Path()
                path.moveTo(0f, 0f)
                path.lineTo(size.width, size.height / 2f)
                path.lineTo(0f, size.height)
                onDrawBehind {
                    drawPath(
                        path,
                        color = Color.White,
                        style = Stroke(
                            width = 7f,
                            pathEffect = PathEffect.cornerPathEffect(2.dp.toPx()),
                            cap = StrokeCap.Round
                        )
                    )
                }
            }
            .width(8.dp)
            .height(15.dp)
    )
}

@Composable
@Preview
fun PreviewChangePosButton(){
    ChangePosButton()
}