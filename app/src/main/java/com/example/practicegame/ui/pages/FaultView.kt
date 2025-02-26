package com.example.practicegame.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicegame.R
import kotlinx.coroutines.delay

@Composable
fun FaultView(
    modifier: Modifier = Modifier,
    openStartScreen: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000)
        openStartScreen()
    }
    Box(
        modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.game_over),
            contentDescription = null,
            modifier
                .padding(top = 203.dp)
                .size(397.dp, 349.dp)
        )
    }
}

@Composable
@Preview
fun PreviewFaultView() {
    FaultView(openStartScreen = {})
}