package org.banking.simple.app.core.data

sealed class Result<out T> {
    data class Error(val message:String):Result<Nothing>()
    data class Success<out T>(val data:T):Result<T>()
    object Loading:Result<Nothing>()
}
