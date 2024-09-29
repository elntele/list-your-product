package com.br.jc.list_your_product.common

sealed class State<out T : Any>
object Loading: State<Nothing>()
class Loaded<out T : Any>(val result: T): State<T>()
class Error(val message: String?): State<Nothing>()
object StandBy: State<Nothing>()