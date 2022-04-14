package com.developersancho.framework.network

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Failure : IOException() {
    object JsonError : Failure()
    object UnknownError : Failure()
    object UnknownHostError : Failure()
    object EmptyResponse : Failure()
    object ConnectivityError : Failure()
    object InternetError : Failure()
    object UnAuthorizedException : Failure()
    object ParsingDataError : Failure()
    object IgnorableError : Failure()
    data class TimeOutError(override var message: String) : Failure()
    data class ApiError(var code: Int = 0, override var message: String) : Failure()
    data class ServerError(var code: Int = 0, override var message: String) : Failure()
    data class NotFoundException(override var message: String) : Failure()
    data class SocketTimeoutError(override var message: String) : Failure()
    data class BusinessError(override var message: String, val stackTrace: String) : Failure()
    data class HttpError(var code: Int, override var message: String) : Failure()
}

fun Throwable.handleThrowable(): Failure {
    // Timber.e(this)
    return if (this is UnknownHostException) {
        Failure.ConnectivityError
    } else if (this is HttpException && this.code() == HttpStatusCode.Unauthorized.code) {
        Failure.UnAuthorizedException
    } else if (this is SocketTimeoutException) {
        Failure.SocketTimeoutError(this.message!!)
    } else if (this.message != null) {
        Failure.NotFoundException(this.message!!)
    } else {
        Failure.UnknownError
    }
}

//fun Exception.toCustomExceptions() = when (this) {
//    is ServerResponseException -> Failure.HttpErrorInternalServerError(this)
//    is ClientRequestException ->
//        when (this.response.status.value) {
//            400 -> Failure.HttpErrorBadRequest(this)
//            401 -> Failure.HttpErrorUnauthorized(this)
//            403 -> Failure.HttpErrorForbidden(this)
//            404 -> Failure.HttpErrorNotFound(this)
//            else -> Failure.HttpError(this)
//        }
//    is RedirectResponseException -> Failure.HttpError(this)
//    else -> Failure.GenericError(this)
//}
