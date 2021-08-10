package learning.android.compose.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import learning.android.compose.R
import learning.android.compose.data.WeatherInfo
import learning.android.compose.ui.theme.Purple500
import learning.android.compose.ui.theme.Teal200
import learning.android.compose.ui.theme.header
import learning.android.compose.ui.theme.text

@Composable
fun WeatherItem(weatherInfo: WeatherInfo, selected: Boolean, onClick: () -> Unit) {
    Surface(shape = RoundedCornerShape(8.dp), elevation = 8.dp,
        color = if (selected) Teal200 else Purple500) {
        Row(Modifier.padding(horizontal = 5.dp).fillMaxWidth(1f).selectable(
            selected = selected,
            onClick = onClick)) {
            Image(painter = painterResource(id = weatherInfo.imageResource),
                contentDescription = "Weather info as image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(weatherInfo.cityName, style = header)
                Text(weatherInfo.description, style = text)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun DummyWeatherItem() {
    WeatherItem(
        WeatherInfo(
            R.drawable.weather_sunny,
            "Dummy Title",
            "A dummy description"
        ), false) {}
}