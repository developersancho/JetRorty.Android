/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.framework.base

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    class Success<T>(val data: T) : ViewState<T>()
    class Error(val error: Throwable) : ViewState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is Loading -> "Loading"
        }
    }
}