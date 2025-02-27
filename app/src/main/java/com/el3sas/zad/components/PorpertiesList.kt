package com.el3sas.zad.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.el3asas.zad.domain.models.PropertiesModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PropertiesList(
    modifier: Modifier = Modifier,
    properties: List<PropertiesModel>,
    horizontalArrangement: Arrangement.Horizontal =
        Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.Start,
        ),
    verticalArrangement: Arrangement.Vertical =
        Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.Top,
        ),
    contentVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    contentHorizontalAlignment: Arrangement.Horizontal = Arrangement.Start,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
    ) {
        properties.forEach { property ->
            PropertyItem(
                property = property,
                contentVerticalAlignment = contentVerticalAlignment,
                contentHorizontalAlignment = contentHorizontalAlignment,
            )
        }
    }
}

@Composable
fun PropertyItem(
    property: PropertiesModel,
    contentVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    contentHorizontalAlignment: Arrangement.Horizontal = Arrangement.Start,
) {
    Row(
        modifier = Modifier,
        verticalAlignment = contentVerticalAlignment,
        horizontalArrangement = contentHorizontalAlignment,
    ) {
        AsyncImage(
            modifier =
                Modifier
                    .size(20.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                    .clip(CircleShape)
                    .padding(2.dp),
            model = property.iconUrl,
            contentScale = ContentScale.Fit,
            contentDescription = property.title,
        )
        Spacer(Modifier.width(4.dp))
        Text(text = property.value)
    }
}

@Preview
@Composable
fun PropertyItemPreview() {
    val property =
        PropertiesModel(
            iconUrl = "https://via.placeholder.com/40",
            title = "Example Title",
            value = "Example Value",
        )
    PropertyItem(
        property = property,
    )
}
