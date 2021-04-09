package com.example.submission_2_fundamental.utils

class Source <out E>(val types: Types, val data: E?, val messages: String?) {
    companion object {
        fun <E> success(data: E): Source<E> = Source(Types.Success, data, null)

        fun <E> error(data: E?, message: String): Source<E> = Source(Types.Error, data, message)

        fun <E> loading(data: E?): Source<E> = Source(Types.Loading, data, null)
    }
}