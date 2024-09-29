package com.br.jc.list_your_product.rest.model

data class Pojo(
    val id : Int,
    val name: String,
    val description:String,
    var url: String="",
    var home: String=""
)
