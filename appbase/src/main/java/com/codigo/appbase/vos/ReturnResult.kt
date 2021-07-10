package com.codigo.appbase.vos



sealed class ReturnResult<out T> {
    class ErrorResult<T>(var errorMsg: Any, var data: T? = null) : ReturnResult<T>()
    class PositiveResult<T>(var data: T) : ReturnResult<T>()
    class ValidationErrorResult<T>(var msg: Any) : ReturnResult<T>()
    object EmptyResult : ReturnResult<Nothing>()
    object Loading : ReturnResult<Nothing>()

    object SessionExpired : ReturnResult<Nothing>()
    object NewVersion : ReturnResult<Nothing>()
    object NetworkErrorResult : ReturnResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is PositiveResult -> {
                "PositiveResult(${this.data ?: "no data content"})"
            }
            is ErrorResult -> {
                "ErrorResult(${this.errorMsg},${this.data ?: "no data content"})"
            }
            is NetworkErrorResult -> {
                "NetworkError"
            }
            is EmptyResult -> {
                "EmptyResult"
            }
            is ValidationErrorResult -> {
                "ValidationErrorResult(${this.msg})"
            }

            is NewVersion -> {
                "NewUpdate"
            }
            is SessionExpired -> {
                "SessionExpired"
            }
            is Loading -> {
                "Loading"
            }
        }
    }

}

fun <T> ReturnResult<T>.compare(other: ReturnResult<T>, inTestMode: Boolean = false): Boolean {
    return if (this.javaClass != other.javaClass)
        false
    else {

        when (this) {
            is ReturnResult.PositiveResult -> {
                this.data != null && (other as ReturnResult.PositiveResult).data != null && this.data!! == other.data
            }
            is ReturnResult.ErrorResult -> {
                this.data == (other as ReturnResult.ErrorResult).data && this.errorMsg == other.errorMsg
            }

            is ReturnResult.ValidationErrorResult -> {
                this.msg == (other as ReturnResult.ValidationErrorResult).msg
            }
            else -> {
                true
            }
        }
    }
}





