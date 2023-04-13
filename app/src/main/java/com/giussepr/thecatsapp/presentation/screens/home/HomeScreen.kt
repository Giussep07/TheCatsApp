package com.giussepr.thecatsapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.giussepr.thecatsapp.domain.model.Cat

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.onUiEvent(HomeViewModel.HomeUiEvent.LoadCats)
    }

    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Catbreeds",
                        textAlign = TextAlign.Center,
                    )
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

            if (viewModel.uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            if (viewModel.uiState.cats.isNotEmpty()) {
                HomeScreenContent(
                    cats = viewModel.uiState.cats,
                )
            }

            if (!viewModel.uiState.isLoading && viewModel.uiState.errorMessage.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = viewModel.uiState.errorMessage,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    cats: List<Cat>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(cats) { cat ->
            CatCardItem(
                cat = cat,
                modifier = Modifier.testTag(cat.id)
            )
        }
    }
}

@Composable
fun CatCardItem(
    cat: Cat,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Cat breed
            Text(
                text = cat.breedName,
                style = MaterialTheme.typography.titleMedium
            )
            // Cat Image
            AsyncImage(
                modifier = Modifier.padding(top = 16.dp),
                model = cat.imageUrl,
                contentDescription = ""
            )
            // Origin and Intelligence
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Origin
                Text(
                    text = "Origin: ${cat.origin}",
                    style = MaterialTheme.typography.bodyMedium
                )
                // Intelligence
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Intelligence: ",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    for (i in 1..cat.intelligence) {
                        Text(
                            text = "⭐",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}