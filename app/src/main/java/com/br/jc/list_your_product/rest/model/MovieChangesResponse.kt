package com.br.jc.list_your_product.rest.model

import com.google.gson.annotations.SerializedName

data class MovieChangesResponse(
    val page: Int,
    @SerializedName("results")
    val movieList: List<ReferenceId>,
    val total_pages: Int,
    val total_results: Int
)
