package com.example.practicegame.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicegame.R


@Composable
fun StartPage(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    score:Int=33
) {
    Box(modifier.fillMaxSize()
        .paint(
            painterResource(id = R.drawable.first_back_image),
            contentScale = ContentScale.FillBounds
        )) {
        Box(
            modifier
                .align(Alignment.TopCenter)
                .padding(top = 279.dp)
                .size(width = 92.dp, height = 66.dp)
                .background(Color(0xFFCA2E2E))

        ){
            Text(text = score.toString(),
                fontSize = 50.sp,
                fontFamily = FontFamily(Font(R.font.sfpro_text_bold)),
                color = Color.White,
                modifier = modifier.align(Alignment.Center)
                   )
        }
        Box(
            modifier
                .align(Alignment.TopCenter)
                .padding(top = 357.dp)
                .size(width = 165.dp, height = 49.dp)
                .background(Color(0xFFCA2E2E))

        ){
            Text(text = "Best Score",
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.sfpro_text_bold)),
                color = Color.White,
                modifier = modifier.align(Alignment.Center)
            )
        }
        Button(
            onClick = { onClick() },
            modifier = modifier
                .padding(top = 596.dp)
                .align(Alignment.TopCenter)
                .size(width = 306.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(
                text = "START GAME",
                color = Color.Black,
                fontSize = 23.sp,
                fontWeight = FontWeight(1000),
                fontFamily = FontFamily(Font(R.font.sfpro_text_bold))
            )
        }
    }
}


@Preview
@Composable
fun StartPagePreview() {
    StartPage(onClick = {})
}
