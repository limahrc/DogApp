package com.encomp.dogapp.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.encomp.dogapp.R

@Composable
fun HomeScreen(
    vm: HomeViewModel = viewModel(),
    onDogBreedsButtonClicked: () -> Unit
) {
    val screenState = vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.bg_bones),
                contentScale = ContentScale.Crop
            )
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        ) {
            Text(
                text = "Dog App",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        SubcomposeAsyncImage(
            model = screenState.value.randomDogImageUrl,
            contentDescription = "random dog image",
            modifier = Modifier
                .size(400.dp)
                .shadow(1.dp, RoundedCornerShape(8.dp)),
            loading = {
                LinearProgressIndicator()
            },
            error = {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.Red.copy(alpha = 0.2f)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
                        Text(
                            text = "Ocorreu um erro ao carregar a imagem.\nTente novamente.",
                        )
                    }
                }
            },
            contentScale = ContentScale.Crop,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { vm.getRandomDog() }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }

            Button(onClick = onDogBreedsButtonClicked) {
                Text(text = "Ver por raça")
            }

            val context = LocalContext.current
            Button(onClick = {
                Toast.makeText(context, "Curtido!", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            }
        }
    }
}