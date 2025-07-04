package com.example.lazycolumn

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.ui.theme.AppTheme
import kotlin.math.round

@Composable
fun IndexTicker(
    indices: List<IndexItem>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        indices.forEachIndexed { i, item ->
            IndexTickerItem(
                item = item, modifier = modifier
                    .padding(4.dp)
                    .weight(1f)
            )

            if (i != indices.size - 1) {
                VerticalDivider(
                    modifier = Modifier
                        .height(24.dp)
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun IndexTickerPreview() {
    AppTheme {
        Surface {
            IndexTicker(
                indices =
                    listOf(
                        IndexItem("Nifty", 25000.0, 0.0, 0.0),
                        IndexItem("Sensex", 80000.0, 0.0, 0.0),
                    ),
                modifier =
                    Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
            )
        }
    }
}

@Composable
fun IndexTickerItem(
    item: IndexItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            item.name.uppercase(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.wrapContentSize(),
        )

        Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
            Text(roundTo2Digits(item.points), style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(2.dp))

            val change =
                roundTo2Digits(item.pointsChange) +
                        " (" +
                        roundTo2Digits(item.percentageChange) + "%)"

            Text(change, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@SuppressLint("DefaultLocale")
private fun roundTo2Digits(value: Double): String {
    return String.format("%.2f", value)
}

@Preview
@Composable
private fun IndexItemPreview() {
    AppTheme {
        Surface {
            IndexTickerItem(
                item =
                    IndexItem("Nifty", 25000.0, 0.0, 0.0),
                modifier =
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
            )
        }
    }
}