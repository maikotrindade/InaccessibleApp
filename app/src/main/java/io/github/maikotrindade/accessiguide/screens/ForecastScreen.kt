package io.github.maikotrindade.accessiguide.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.maikotrindade.accessiguide.R
import io.github.maikotrindade.accessiguide.screens.mock.citiesWeather
import io.github.maikotrindade.accessiguide.ui.theme.AccessiguideTheme
import kotlin.random.Random

@Composable
fun ForecastScreen() {
    AccessiguideTheme {
        Scaffold { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.TopCenter
            ) {
                ForecastContent()
            }
        }
    }
}

@Composable
fun ForecastContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        ForecastHeader(title = "Cities and Weather")
        ForecastList(citiesWeather = citiesWeather)
    }
}

@Composable
fun ForecastHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun ForecastList(citiesWeather: List<Pair<String, String>>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(citiesWeather) { (city, weather) ->
            ForecastCard(city = city, weather = weather)
        }
    }
}

@Composable
fun ForecastCard(city: String, weather: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text(
                    text = city,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(12.dp))
                RandomCloudyImage()
            }

            Text(
                text = weather,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun RandomCloudyImage() {
    val imageOptions = listOf(
        R.drawable.cloudy to "",
        R.drawable.cloudy_day to "",
        R.drawable.clouds to ""
    )
    val randomIndex = remember { mutableIntStateOf(Random.nextInt(imageOptions.size)) }
    val (randomImageId, contentDescription) = imageOptions[randomIndex.intValue]

    Image(
        painter = painterResource(id = randomImageId),
        contentDescription = contentDescription,
        modifier = Modifier.size(36.dp)
    )
}
