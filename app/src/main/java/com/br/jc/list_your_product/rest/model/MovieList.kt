package com.br.jc.list_your_product.rest.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
