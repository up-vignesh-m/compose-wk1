package com.example.lazycolumn

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lazycolumn.ui.theme.AppTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    AppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            val ticks by viewModel.ticks.collectAsStateWithLifecycle()

            LazyColumn(
                modifier = modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { IndexTicker(ticks) }

                item { Header() }

                items(10) { index ->
                    ListItem(index)
                }

                items(indices) { item ->
                    Surface(color = MaterialTheme.colorScheme.tertiaryContainer) {
                        IndexTickerItem(
                            item = item,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                item { Footer() }


            }
        }
    }
}

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