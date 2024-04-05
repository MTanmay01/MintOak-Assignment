package com.mtanmay.domain.repository

import com.mtanmay.domain.models.ParentItem
import com.mtanmay.domain.response.JSONResponse

interface JSONDataRepository {
    suspend fun loadJsonData(fileName: String): JSONResponse<List<ParentItem>>
}