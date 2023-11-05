package com.encomp.dogapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.encomp.dogapp.data.DogApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val api = DogApi.create()

    val uiState = MutableStateFlow(HomeScreenState())

    init {
        getRandomDog()
    }

    fun getRandomDog() {
        viewModelScope.launch {
            try {
                val response = api.getRandomDog()

                if (response.isSuccessful) {
                    val dog = response.body()?.message.orEmpty()
                    uiState.update {
                        it.copy(
                            randomDogImageUrl = dog
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error getting random dog", e)
            }
        }
    }
}