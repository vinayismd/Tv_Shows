package com.vinayismd.tvshowapp.repository

sealed class Response<T>{
    data class Success<T>(val data: T?): Response<T>()
    data class Error<T>(val errorMsg: String?): Response<T>()
    class Loading<T>(): Response<T>()
}
