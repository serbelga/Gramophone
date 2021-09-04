package com.android.sergiobelda.gramophone.data

sealed class Result<out A> {
    data class Success<out A>(val value: A?) : Result<A>()
    data class Error(
        val code: Int? = null,
        val message: String? = null,
        val exception: Throwable? = null
    ) : Result<Nothing>()

    object Loading : Result<Nothing>()

    fun <B> map(m: ((A) -> B)): Result<B> = when (this) {
        is Success -> Success(this.value?.let { m(it) })
        is Error -> Error(this.code, this.message, this.exception)
        is Loading -> Loading
    }
}
