package com.encomp.dogapp.ui.screens.breeds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BreedsScreen(
    viewModel: BreedsViewModel = viewModel(),
    onBreedClicked: (String) -> Unit,
    onBackButtonClicked: () -> Unit
) {
    val screenState = viewModel.uiState.collectAsState()

    if (screenState.value.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(screenState.value.breeds) { breed ->
                BreedItem(
                    breed = breed,
                    onBreedClicked = onBreedClicked
                )
            }
        }
    }
}

@Composable
fun BreedItem(
    breed: String,
    onBreedClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onBreedClicked(breed) }
    ) {
        Text(text = breed, color = Color.Black)
    }
}