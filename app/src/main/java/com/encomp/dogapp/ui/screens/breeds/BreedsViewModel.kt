package com.encomp.dogapp.ui.screens.breeds

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.encomp.dogapp.data.DogApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreedsViewModel : ViewModel() {

    private val api = DogApi.create()

    val uiState = MutableStateFlow(BreedsScreenState(isLoading = true))

    init {
        getBreeds()
    }

    private fun getBreeds() {
        viewModelScope.launch {
            try {
                val response = api.getDogBreeds()
                if (response.isSuccess) {
                    uiState.update {
                        it.copy(
                            isLoading = false,
                            breeds = response.getOrThrow().message
                        )
                    }
                } else {
                    uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}