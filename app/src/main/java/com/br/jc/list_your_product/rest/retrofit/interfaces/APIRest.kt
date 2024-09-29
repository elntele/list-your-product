package com.br.jc.list_your_product.rest.retrofit.interfaces

import com.br.jc.list_your_product.rest.model.MovieChangesResponse
import com.br.jc.list_your_product.rest.model.Pojo
import com.br.jc.list_your_product.rest.model.ReferenceId
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIRest {
    @GET("users/{pojoId}/repos")
    suspend fun getPojos(@Path("pojoId") pojoId: String?): List<Pojo?>?

    @GET("movie/changes")
    suspend fun getIds(@Query("page") page: String):MovieChangesResponse
}