package com.developersancho.framework.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.developersancho.framework.network.interceptor.HttpRequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val CONTENT_LENGTH = 250_000L
private const val CLIENT_TIME_OUT = 60L
private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
private const val CLIENT_CACHE_DIRECTORY = "http"

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

fun createCache(context: Context): Cache = Cache(
    directory = File(context.cacheDir, CLIENT_CACHE_DIRECTORY),
    maxSize = CLIENT_CACHE_SIZE
)

fun createHttpLoggingInterceptor(isDev: Boolean = true): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (isDev) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

fun createHttpRequestInterceptor(): HttpRequestInterceptor {
    return HttpRequestInterceptor()
}

fun createChuckInterceptor(context: Context): ChuckerInterceptor {
    // Create the Collector
    val chuckerCollector = ChuckerCollector(
        context = context,
        // Toggles visibility of the push notification
        showNotification = true,
        // Allows to customize the retention period of collected data
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    return ChuckerInterceptor.Builder(context)
        // The previously created Collector
        .collector(chuckerCollector)
        // The max body content length in bytes, after this responses will be truncated.
        .maxContentLength(CONTENT_LENGTH)
        // List of headers to replace with ** in the Chucker UI
        .redactHeaders("Auth-Token", "Bearer")
        // Read the whole response body even when the client does not consume the response completely.
        // This is useful in case of parsing errors or when the response body
        // is closed before being read like in Retrofit with Void and Unit types.
        .alwaysReadResponseBody(true)
        .build()
}

fun createOkHttpClient(
    isDev: Boolean = true,
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addInterceptor(createHttpLoggingInterceptor(isDev))
        if (isDev) {
            addInterceptor(createChuckInterceptor(context))
            addInterceptor(createHttpRequestInterceptor())
        }
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }.build()
}

fun createOkHttpClient(
    isDev: Boolean = true,
    isCache: Boolean = false,
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if (isCache) cache(createCache(context))
        addInterceptor(createHttpLoggingInterceptor(isDev))
        if (isDev) {
            addInterceptor(createChuckInterceptor(context))
            addInterceptor(createHttpRequestInterceptor())
        }
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }.build()
}

fun createOkHttpClient(
    isCache: Boolean = false,
    interceptors: MutableList<Interceptor> = mutableListOf(),
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if (isCache) cache(createCache(context))
        interceptors.forEach { addInterceptor(it) }
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }.build()
}

fun createOkHttpClient(
    isCache: Boolean = false,
    vararg interceptors: Interceptor,
    context: Context
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if (isCache) cache(createCache(context))
        interceptors.forEach { addInterceptor(it) }
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }.build()
}


/**
 * Create Retrofit Client with Moshi
 *
 * <reified T> private func let us using reflection.
 * We can use generics and reflection so ->
 *  we don't have to define required NewsApi Interface here
 */
inline fun <reified T> createRetrofitWithMoshi(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    return retrofit.create(T::class.java)
}