package com.mtanmay.data.repository

import com.mtanmay.data.datasource.JSONDataSource
import com.mtanmay.data.models.toParentItemMID
import com.mtanmay.data.models.toParentItemTID
import com.mtanmay.domain.models.BaseParentItem
import com.mtanmay.domain.repository.JSONDataRepository
import com.mtanmay.domain.response.JSONResponse
import javax.inject.Inject

class JSONDataRepositoryImpl @Inject constructor(
    private val dataSource: JSONDataSource
): JSONDataRepository {
    override suspend fun loadJsonData(fileName: String, sortByMId: Boolean): JSONResponse<List<BaseParentItem>> {
        return try {
            val data = dataSource.loadJsonData(fileName)
            if (data != null) {
                if (sortByMId)
                    JSONResponse.Success(data.toParentItemMID())
                else
                    JSONResponse.Success(data.toParentItemTID())
            }
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