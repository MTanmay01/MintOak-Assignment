package com.mtanmay.data.repository

import com.mtanmay.data.datasource.JSONDataSource
import com.mtanmay.data.models.JSONData
import com.mtanmay.data.models.toParentItem
import com.mtanmay.domain.models.ParentItem
import com.mtanmay.domain.repository.JSONDataRepository
import com.mtanmay.domain.response.JSONResponse
import javax.inject.Inject

class JSONDataRepositoryImpl @Inject constructor(
    private val dataSource: JSONDataSource
): JSONDataRepository {
    override suspend fun loadJsonData(fileName: String): JSONResponse<List<ParentItem>> {
        return try {
            val data = dataSource.loadJsonData(fileName)
            if (data != null)
                JSONResponse.Success(data.toParentItem())
            else {
                JSONResponse.Error("Failed to convert JSON")
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            JSONResponse.Error("Failed to convert JSON")
        }
    }

}