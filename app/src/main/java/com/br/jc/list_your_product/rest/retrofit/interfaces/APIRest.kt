package com.br.jc.list_your_product.rest.retrofit.interfaces

import com.br.jc.list_your_product.rest.model.MovieChangesResponse
import com.br.jc.list_your_product.rest.model.Movie
import com.br.jc.list_your_product.rest.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIRest {
    @GET("users/{pojoId}/repos")
    suspend fun getPojos(@Path("pojoId") pojoId: String?): List<Movie?>?

    @GET("movie/changes")
    suspend fun getIds(@Query("page") page: String): MovieChangesResponse


    @GET("discover/movie")
    suspend fun getMovies(
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("sort_by") sort_by: String,
    ): MovieList



    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("language") language: String,
        @Query("page") page: String,
    ): MovieList

    /*curl --request GET \
     --url 'https://api.themoviedb.org/3/search/movie?query=star%20wars&include_adult=false&language=pt-BRT&page=1' \
     --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjZWVjNzMzYjQxYzEyY2I5NDk3NWJjYmRjZGNiYTNhYSIsIm5iZiI6MTcyNzk2NzE2My43NzIyMjgsInN1YiI6IjYxOGZlMjllY2I2ZGI1MDA2MjQ2NTJlMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.yMrpolSlgXcWuhDbKCfuJkdkAETAZN-vN3NqGr9dZQ4' \
     --header 'accept: application/json'*/
}