package com.br.jc.list_your_product.rest.usecase

import com.br.jc.list_your_product.common.interfaces.UseCase
import com.br.jc.list_your_product.rest.model.Movie
import com.br.jc.list_your_product.rest.retrofit.RetrofitService

class RestSearchUseCase: UseCase<String, List<Movie>> {
    override suspend fun execute(param: String): List<Movie> {
       val result= RetrofitService.apiRest.searchMovies(
           param,
           true,
           "pt-BRT",
           "1"
       )
        return result.movieList
    }
}