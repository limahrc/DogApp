package com.encomp.dogapp.data

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DogApi {

    @GET("breeds/image/random")
    suspend fun getRandomDog(): Result<DogResponse>

    @GET("breeds/list")
    suspend fun getDogBreeds(): Result<DogBreedResponse>


    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"

        private val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        fun create(): DogApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    okhttp3.OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogApi::class.java)
        }
    }
}