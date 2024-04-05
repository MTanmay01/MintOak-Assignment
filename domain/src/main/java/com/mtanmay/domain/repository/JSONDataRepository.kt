package com.mtanmay.domain.repository

import com.mtanmay.domain.models.BaseParentItem
import com.mtanmay.domain.response.JSONResponse

interface JSONDataRepository {
    suspend fun loadJsonData(fileName: String, sortByMid: Boolean): JSONResponse<List<BaseParentItem>>
}