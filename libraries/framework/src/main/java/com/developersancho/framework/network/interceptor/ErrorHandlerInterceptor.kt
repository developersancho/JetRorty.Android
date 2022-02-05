package com.developersancho.framework.network.interceptor

import android.content.Context
import com.developersancho.framework.extension.isInternetAvailable
import com.developersancho.framework.network.Failure
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandlerInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (context.isInternetAvailable().not()) {
            throw Failure.ConnectivityError
        }

        val response = try {
            chain.proceed(chain.request())
        } catch (ex: Exception) {
            throw when (ex) {
                is UnknownHostException, is IllegalArgumentException -> Failure.UnknownHostError
                is HttpException -> Failure.HttpError(ex.code(), ex.message())
                is SocketTimeoutException -> Failure.TimeOutError(ex.message.toString())
                else -> IOException(ex)
            }
        }

        return when (response.isSuccessful) {
            true -> {
                response.body?.let {
                    response
                } ?: run {
                    throw Failure.EmptyResponse
                }
            }
            false -> throw Failure.ApiError(response.code, response.message)
        }
    }
}