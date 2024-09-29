package com.br.jc.list_your_product.rest.usecase

import com.br.jc.list_your_product.common.interfaces.UseCase
import com.br.jc.list_your_product.rest.retrofit.RetrofitService
import com.br.jc.list_your_product.rest.model.ReferenceId

class RestGetIdsUseCase : UseCase<String, List<ReferenceId>> {
    override suspend fun execute(param: String): List<ReferenceId> {
        val result = RetrofitService.apiRest.getIds("1")
        val idList: List<ReferenceId> = result.let { response ->
            // Transforma a lista interna de movieList em uma lista de ReferenceId
            response.movieList.map { referenceId ->
                ReferenceId(id = referenceId.id, adult = referenceId.adult)
            }
        }
        return idList
    }
}