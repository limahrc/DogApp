package com.encomp.dogapp.ui.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.encomp.dogapp.data.DogApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val api = DogApi.create()

    val uiState = MutableStateFlow(DetailScreenState())

    fun getDogImage(breed: String) {
        viewModelScope.launch {
            try {
                val response = api.getRandomDogByBreed(breed)

                if (response.isSuccessful) {
                    val dog = response.body()?.message.orEmpty()

                    uiState.update {
                        it.copy(
                            imageUrl = dog
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Error getting dog image", e)
            }
        }
    }
}