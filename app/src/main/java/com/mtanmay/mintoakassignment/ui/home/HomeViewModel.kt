package com.mtanmay.mintoakassignment.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtanmay.data.repository.JSONDataRepositoryImpl
import com.mtanmay.domain.models.ParentItem
import com.mtanmay.domain.response.JSONResponse
import com.mtanmay.mintoakassignment.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryImpl: JSONDataRepositoryImpl
): ViewModel() {

    private val _parentItemsFlow = MutableStateFlow<JSONResponse<List<ParentItem>>>(JSONResponse.Loading)
    val parentItemFlow: StateFlow<JSONResponse<List<ParentItem>>>
        get() = _parentItemsFlow.asStateFlow()

    init {
        getJsonData()
    }

    private fun getJsonData() {
        viewModelScope.launch {
            when (val parentItem = repositoryImpl.loadJsonData(BuildConfig.JSON_FILE)) {
                JSONResponse.Loading -> {
                    _parentItemsFlow.update { JSONResponse.Loading }
                }
                is JSONResponse.Success -> {
                    _parentItemsFlow.update {
                        JSONResponse.Success(parentItem.data)
                    }
                }
                is JSONResponse.Error -> {
                    _parentItemsFlow.update {
                        JSONResponse.Error(parentItem.errorMsg)
                    }
                }
            }
        }
    }

}