package com.developersancho.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.developersancho.framework.network.interceptor.ErrorHandlerInterceptor
import com.developersancho.framework.network.interceptor.HttpRequestInterceptor
import com.developersancho.remote.BuildConfig
import com.developersancho.remote.service.CharacterService
import com.developersancho.remote.service.EpisodeService
import com.developersancho.remote.service.LocationService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "base_url"
private const val CONTENT_LENGTH = 250_000L

private const val CLIENT_TIME_OUT = 60L
private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
private const val CLIENT_CACHE_DIRECTORY = "http"
private const val CLIENT_MOSHI = "moshi"

fun remoteModule() = module {
    single(named(BASE_URL)) { provideBaseUrl() }
    single(named(CLIENT_MOSHI)) { provideMoshi() }
    single { provideCache(androidContext()) }
    single { provideHttpLoggingInterceptor() }
    single { provideHttpRequestInterceptor() }
    single { provideErrorHandlerInterceptor(androidContext()) }
    single { provideChuckInterceptor(androidContext()) }
    single { provideOkHttpClient(get(), get(), get(), get(), get()) }

    factory {
        provideRetrofit<CharacterService>(
            get(),
            get(named(CLIENT_MOSHI)),
            get(named(BASE_URL))
        )
    }

    factory {
        provideRetrofit<EpisodeService>(
            get(),
            get(named(CLIENT_MOSHI)),
            get(named(BASE_URL))
        )
    }

    factory {
        provideRetrofit<LocationService>(
            get(),
            get(named(CLIENT_MOSHI)),
            get(named(BASE_URL))
        )
    }
}


fun provideBaseUrl(): String {
    return BuildConfig.BASE_URL
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

fun provideCache(context: Context): Cache = Cache(
    File(
        context.cacheDir,
        CLIENT_CACHE_DIRECTORY
    ),
    CLIENT_CACHE_SIZE
)

/**
 * return HttpLoggingInterceptor
 * @param isDebug
 */
fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

fun provideHttpRequestInterceptor(): HttpRequestInterceptor {
    return HttpRequestInterceptor()
}

fun provideErrorHandlerInterceptor(context: Context): ErrorHandlerInterceptor {
    return ErrorHandlerInterceptor(context)
}

fun provideChuckInterceptor(context: Context): ChuckerInterceptor {
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

fun provideOkHttpClient(
    cache: Cache,
    httpLoggingInterceptor: HttpLoggingInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
    httpRequestInterceptor: HttpRequestInterceptor,
    errorHandlerInterceptor: ErrorHandlerInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder().apply {
        cache(cache)
        addInterceptor(httpLoggingInterceptor)
        addInterceptor(errorHandlerInterceptor)
        if (BuildConfig.DEBUG) {
            addInterceptor(chuckerInterceptor)
            addInterceptor(httpRequestInterceptor)
        }
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }

    return builder.build()
}

/**
 * Create Retrofit Client
 *
 * <reified T> private func let us using reflection.
 * We can use generics and reflection so ->
 *  we don't have to define required NewsApi Interface here
 */
inline fun <reified T> provideRetrofit(
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