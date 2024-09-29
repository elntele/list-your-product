package com.br.jc.list_your_product.common.interfaces

interface UseCase<Param, Return> {
    suspend fun execute(param: Param): Return
}