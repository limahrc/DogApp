package com.encomp.dogapp.data

import retrofit2.Retrofit
import retrofit2.http.GET

interface DogApi {

    @GET("breeds/image/random")
    suspend fun getRandomDog(): DogResponse

    @GET("breeds/list")
    suspend fun getDogBreeds(): DogBreedResponse


    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"

        fun create(): DogApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
                .create(DogApi::class.java)
        }
    }
}