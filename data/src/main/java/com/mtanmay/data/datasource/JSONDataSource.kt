package com.mtanmay.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.mtanmay.data.di.DispatchersModule.Dispatcher
import com.mtanmay.data.di.DispatchersModule.DispatcherType
import com.mtanmay.data.models.JSONData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JSONDataSource @Inject constructor(
    private val context: Context,
    @Dispatcher(DispatcherType.IO) private val ioDispatcher: CoroutineDispatcher
) {
    /**
     * Read the contents of a JSON file and serialize to [JSONData]
     * @param fileName name of the json file
     * @return [JSONData] if serialization using successful or null if unsuccessful
     */
    suspend fun loadJsonData(
        fileName: String
    ): JSONData? = withContext(ioDispatcher) {
        return@withContext try {
            val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
            Gson().fromJson(json, JSONData::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}