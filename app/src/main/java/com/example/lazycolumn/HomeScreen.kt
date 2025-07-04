package com.example.lazycolumn

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.ui.theme.AppTheme
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    AppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LazyColumn(modifier = modifier.padding(innerPadding)) {
                item {

                }

                items(10) { index ->

                }

                items(indices) { item ->

                }

                item {

                }


            }
        }
    }
}

private val indices =
    listOf(
        Index("Nifty", 10000.0),
        Index("Sensex", 50000.0),
        Index("Bank Nifty", 20000.0)
    )

data class Index @OptIn(ExperimentalUuidApi::class) constructor(
    val name: String,
    val points: Double,
    val id: String = Uuid.random().toString()
)

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.inverseSurface) {
        Text(
            "This is the footer", modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.primaryContainer) {
        Text(
            "This is the header", modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun IndexItem(index: Index, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.tertiaryContainer) {
        Text(
            index.name.uppercase() + ": " + index.points,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun ListItem(index: Int, modifier: Modifier = Modifier) {
    Surface(color = MaterialTheme.colorScheme.inversePrimary) {
        Text(
            "This is item #$index",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}