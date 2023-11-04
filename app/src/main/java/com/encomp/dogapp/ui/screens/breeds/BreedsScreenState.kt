package com.encomp.dogapp.ui.screens.breeds

data class BreedsScreenState(
    val breeds: List<String> = emptyList(),
    val isLoading: Boolean = true
)
