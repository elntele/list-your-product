package com.br.jc.list_your_product.login.model

data class User(
    val email: String,
    val password: String,
    var nome: String = ""
)
