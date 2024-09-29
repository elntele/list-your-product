package com.br.jc.list_your_product.rest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.jc.list_your_product.rest.usecase.RestGetIdsUseCase
import com.br.jc.list_your_product.rest.model.ReferenceId
import kotlinx.coroutines.launch

class WaitRestViewModel(
    private val restgetIdsUseCase: RestGetIdsUseCase
) : ViewModel() {
    private val _idsReferences = MutableLiveData<List<ReferenceId>>()
    var idsReferences = _idsReferences

    fun getIdsList() {
        viewModelScope.launch {
            val result = restgetIdsUseCase.execute("")
            result?.let {
                // Remove todos os elementos nulos
                val nonNullPojos = it.filterNotNull()
                _idsReferences.postValue(nonNullPojos)
            }
        }
    }

    fun getMovieList(ids: List<String>) {
            viewModelScope.launch {
                val result = restgetIdsUseCase
            }
    }
}