package learning.android.dogbreeds.ui.screens.breeds.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import learning.android.dogbreeds.R
import learning.android.dogbreeds.ui.theme.*


@Composable
fun BreedCard(
    name: String,
    height: String,
    weight: String,
    description: String,
    imgUrl: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 5.dp,
        backgroundColor = Pink
    ) {
        Row {
            BreedImage(imgUrl)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                BreedDetails(name, weight, height, description)
            }
        }
    }
}

@Composable
private fun BreedImage(imgUrl: String) {
    Surface(
        elevation = 5.dp,
        color = Mystic,
        shape = CircleShape,
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 4.dp)
            .border(2.dp, CareysPink, CircleShape)
    ) {
        Image(
            painter = rememberImagePainter(
                data = imgUrl,
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(3.dp)
                .size(100.dp)
        )
    }
}

@Composable
private fun BreedDetails(name: String, weight: String, height: String, description: String) {
    Text(
        text = name,
        style = header
    )
    Column {
        BreedSpecs(weight, height)
        Text(
            text = description,
            style = Typography.body1,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun BreedSpecs(weight: String, height: String) {
    Row {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(
                    data = R.drawable.weight_icon,
                    builder = { transformations(CircleCropTransformation()) }
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
            )
            Text(
                text = "$weight (kg)",
                style = Typography.caption
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(
                    data = R.drawable.height_icon,
                    builder = { transformations(CircleCropTransformation()) }
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
            )
            Text(
                text = "$height (cm)",
                style = Typography.caption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BreedCardPreview() {
    DogBreedsTheme {
        BreedCard(
            "Dog Name",
            "Dog Height",
            "Dog Weight",
            "Dog Description",
            "Dog Image"
        )
    }
}