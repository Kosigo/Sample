package com.kosigo.showcase.library.bestsellers.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosigo.showcase.library.base.presentation.extension.cancelIfActive
import com.kosigo.showcase.library.bestsellers.data.model.State
import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel
import com.kosigo.showcase.library.bestsellers.domain.usecase.GetProductsListUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class BestsellersViewModel constructor(
    private val getProductsListUseCase: GetProductsListUseCase
) : ViewModel() {

    private val _listData = MutableStateFlow<State<List<ProductDomainModel>>>(State.loading())
    private var viewModelJob: Job? = null
    private val query = MutableStateFlow("")
    val listData: StateFlow<State<List<ProductDomainModel>>> get() = _listData

    init {
        viewModelJob = viewModelScope.launch {
            getProductsListUseCase.execute()
                .collect { listView -> _listData.value = listView }
        }

        viewModelScope.launch {
            query
                .debounce(300)
                .flatMapLatest { getProductsListUseCase.executeSearch(it) }
                .distinctUntilChanged()
                .collect { listView -> _listData.value = listView }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelIfActive()
    }

    fun onSearch(textSearch: String) {
        query.value = textSearch
    }

}






