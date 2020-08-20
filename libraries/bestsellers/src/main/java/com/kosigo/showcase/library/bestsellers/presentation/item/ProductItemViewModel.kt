package com.kosigo.showcase.library.bestsellers.presentation.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosigo.showcase.library.base.presentation.extension.cancelIfActive
import com.kosigo.showcase.library.bestsellers.data.model.State
import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel
import com.kosigo.showcase.library.bestsellers.domain.usecase.GetProductUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class ProductItemViewModel constructor(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _itemData = MutableStateFlow<State<ProductDomainModel>>(State.loading())
    private var viewModelJob: Job? = null

    val itemData: StateFlow<State<ProductDomainModel>> get() = _itemData

    fun setIdProduct(id: String) {
        viewModelJob = viewModelScope.launch {
            getProductUseCase.execute(id)
                .collect { item -> _itemData.value = item }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelIfActive()
    }
}
