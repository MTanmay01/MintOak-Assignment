package com.mtanmay.mintoakassignment.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtanmay.data.repository.JSONDataRepositoryImpl
import com.mtanmay.domain.models.BaseParentItem
import com.mtanmay.domain.models.ParentItemMID
import com.mtanmay.domain.models.ParentItemTID
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

    private val _parentItemsFlow = MutableStateFlow<JSONResponse<List<BaseParentItem>>>(JSONResponse.Loading)
    val parentItemFlow: StateFlow<JSONResponse<List<BaseParentItem>>>
        get() = _parentItemsFlow.asStateFlow()

    init {
        getJsonData(true)
    }

    fun getJsonData(sortByMid: Boolean) {
        viewModelScope.launch {
            when (val parentItem = repositoryImpl.loadJsonData(BuildConfig.JSON_FILE, sortByMid)) {
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