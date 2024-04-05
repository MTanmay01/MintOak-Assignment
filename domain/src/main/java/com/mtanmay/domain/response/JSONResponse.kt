package com.mtanmay.domain.response

sealed class JSONResponse<out T> {
    data class Success<out T>(val data: T): JSONResponse<T>()
    data class Error(val errorMsg: String): JSONResponse<Nothing>()
    data object Loading: JSONResponse<Nothing>()
}