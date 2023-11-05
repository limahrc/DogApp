package com.encomp.dogapp.ui.screens.breeds

import android.util.Log
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

                if (response.isSuccessful) {
                    val breeds = response.body()?.message ?: emptyList()
                    uiState.update {
                        it.copy(
                            isLoading = false,
                            breeds = breeds
                        )
                    }
                } else {
                    uiState.update {
                        it.copy(
                            isLoading = false,
                            hasError = true,
                            errorMessage = response.message()
                        )
                    }
                }
            } catch (e: Exception) {
                uiState.update {
                    it.copy(
                        isLoading = false,
                        hasError = true,
                        errorMessage = e.message ?: "Unknown error"
                    )
                }
                Log.e("BreedsViewModel", "getBreeds: ", e)
            }
        }
    }
}