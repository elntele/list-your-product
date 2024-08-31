package com.br.jc.list_your_product.base

interface UseCase<Param, Return> {
    suspend fun execute(param: Param): Return
}