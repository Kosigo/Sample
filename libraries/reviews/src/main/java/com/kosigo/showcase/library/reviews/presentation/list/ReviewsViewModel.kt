package com.kosigo.showcase.library.reviews.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosigo.showcase.library.base.presentation.extension.cancelIfActive
import com.kosigo.showcase.library.reviews.data.model.State
import com.kosigo.showcase.library.reviews.domain.model.ReviewsDomainModel
import com.kosigo.showcase.library.reviews.domain.usecase.GetProductsListUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class ReviewsViewModel constructor(
    private val getProductsListUseCase: GetProductsListUseCase
) : ViewModel() {

    private val _listData = MutableStateFlow<State<List<ReviewsDomainModel>>>(State.loading())
    private var viewModelJob: Job? = null
    val listData: StateFlow<State<List<ReviewsDomainModel>>> get() = _listData

    init {
        viewModelJob = viewModelScope.launch {
            getProductsListUseCase.execute()
                .collect { listView -> _listData.value = listView }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelIfActive()
    }

}






