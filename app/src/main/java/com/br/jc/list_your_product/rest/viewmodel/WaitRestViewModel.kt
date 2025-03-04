package com.br.jc.list_your_product.rest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.jc.list_your_product.rest.model.Movie
import com.br.jc.list_your_product.rest.usecase.RestGetIdsUseCase
import com.br.jc.list_your_product.rest.model.ReferenceId
import com.br.jc.list_your_product.rest.usecase.RestGetDiscoverMoviesListUseCase
import com.br.jc.list_your_product.rest.usecase.RestSearchUseCase
import com.br.jc.list_your_product.util.SingleLiveEvent
import kotlinx.coroutines.launch

class WaitRestViewModel(
    private val restGetIdsUseCase: RestGetIdsUseCase,
    private val restGetDiscoverMoviesListUseCase: RestGetDiscoverMoviesListUseCase,
    private val restSearchUseCase: RestSearchUseCase,
) : ViewModel() {
    private val _idsReferences = MutableLiveData<List<ReferenceId>>()
    var idsReferences = _idsReferences
    private val _moviesFromSearch = MutableLiveData<List<Movie>>()
    var moviesFromSearch: LiveData<List<Movie>> = _moviesFromSearch
    private val _moviesFromDisCover = MutableLiveData<List<Movie>>()
    var moviesFromDisCover: LiveData<List<Movie>> = _moviesFromDisCover






    fun getIdsList() {
        viewModelScope.launch {
            val result = restGetIdsUseCase.execute("")
            result?.let {
                // Remove todos os elementos nulos
                val nonNullPojos = it.filterNotNull()
                _idsReferences.postValue(nonNullPojos)
            }
        }
    }

    fun getDiscoverMovieList() {
        viewModelScope.launch {
            val result = restGetDiscoverMoviesListUseCase.execute("")
            _moviesFromDisCover.postValue(result)
        }
    }

    fun searchMovie(name: String) {
        viewModelScope.launch {
            val result = restSearchUseCase.execute(name)
            _moviesFromSearch.postValue(result)
            println(result)
        }
    }



    fun setMoviesFromSearch(list:List<Movie>){
        _moviesFromSearch.value=list
    }



}