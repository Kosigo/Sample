package com.kosigo.showcase.library.restaurants.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosigo.showcase.library.base.presentation.extension.cancelIfActive
import com.kosigo.showcase.library.restaurants.data.model.State
import com.kosigo.showcase.library.restaurants.domain.model.RestaurantDomainModel
import com.kosigo.showcase.library.restaurants.domain.usecase.GetRestaurantsListUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class RestaurantsViewModel constructor(
    private val getRestaurantsListUseCase: GetRestaurantsListUseCase
) : ViewModel() {

    private val _listData = MutableStateFlow<State<List<RestaurantDomainModel>>>(State.loading())
    private var viewModelJob: Job? = null
    private val query = MutableStateFlow("")
    val listData: StateFlow<State<List<RestaurantDomainModel>>> get() = _listData

    init {
        viewModelJob = viewModelScope.launch {
            getRestaurantsListUseCase.execute()
                .collect { listView -> _listData.value = listView }
        }

        viewModelScope.launch {
            query
                .debounce(300)
                .flatMapLatest { getRestaurantsListUseCase.executeSearch(it) }
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






