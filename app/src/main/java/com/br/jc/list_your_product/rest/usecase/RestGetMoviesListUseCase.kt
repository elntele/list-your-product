package com.br.jc.list_your_product.rest.usecase

import com.br.jc.list_your_product.common.interfaces.UseCase
import com.br.jc.list_your_product.rest.model.Movie
import com.br.jc.list_your_product.rest.model.MovieList
import com.br.jc.list_your_product.rest.retrofit.RetrofitService

class RestGetMoviesListUseCase : UseCase<String, List<Movie>> {
    override suspend fun execute(param: String): List<Movie> {
        var movieList= ArrayList<Movie>()
        for (i in  1..2){
            val result = RetrofitService.apiRest.getMovies(
                true,
                false,
                "pt-BRT",
                "1",
                "popularity.desc"
            )
           movieList.addAll(result.movieList)
        }
        return movieList
    }
}