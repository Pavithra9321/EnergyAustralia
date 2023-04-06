package com.energyaustralia.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.energyaustralia.data.api.model.Bands
import com.energyaustralia.data.api.model.MusicFestival

@Composable
fun HomeScreen() {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val state by homeViewModel.state.collectAsState()

    Scaffold(
        backgroundColor = Color.White,
        content = {
            LazyColumn {

                item {
                    ScreenTitle()
                }
                if (homeViewModel.errorMessage?.isNotEmpty() == true) {
                    item {
                        ErrorMessage(homeViewModel.errorMessage.toString())
                    }
                } else {
                    items(state) { musicFestival: MusicFestival ->
                        if (!musicFestival.name.isNullOrEmpty())
                            MusicFestivalCard(musicFestival = musicFestival)
                    }
                }
            }
        }
    )
}


@Composable
fun MusicFestivalCard(musicFestival: MusicFestival) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                Arrangement.Center
            ) {
                Text(
                    text = musicFestival.name.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                    )
                )

                Column {
                    val bandsList = musicFestival.bands.sortedBy { it.name }
                    bandsList.forEach { bands ->
                        Row(modifier = Modifier.padding(5.dp)) {
                            MusicFestivalCardItem(bands = bands)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ErrorMessage(message: String) {

    Row(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier.weight(1f),
            Arrangement.Center
        ) {
            Text(
                text = message,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}


@Composable
fun ScreenTitle() {

    Row(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier.weight(1f),
            Arrangement.Center
        ) {
            Text(
                text = "Music Festival",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}

@Composable
fun MusicFestivalCardItem(bands: Bands) {

    Row(modifier = Modifier.padding(5.dp)) {
        Column(
            modifier = Modifier.weight(1f),
            Arrangement.Center
        ) {
            Text(
                text = bands.name.toString(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            )

            if (!bands.recordLabel.isNullOrEmpty()) {
                Text(
                    text = bands.recordLabel.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
        }
    }
}























