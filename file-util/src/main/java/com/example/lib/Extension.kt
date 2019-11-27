package com.example.lib

fun <T> List<T>.nullIfEmpty(): List<T>? {
    return if (this.isEmpty())
        null
    else
        this
}

fun <T> ifNull(value: T?, exec: ()-> T): T{
    return value ?: exec()
}