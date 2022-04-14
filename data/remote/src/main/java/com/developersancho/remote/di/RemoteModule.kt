package com.developersancho.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.developersancho.framework.base.app.NetworkConfig
import com.developersancho.framework.network.*
import com.developersancho.framework.network.interceptor.HttpRequestInterceptor
import com.developersancho.remote.service.CharacterService
import com.developersancho.remote.service.EpisodeService
import com.developersancho.remote.service.LocationService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "base_url"

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    @Named(value = BASE_URL)
    fun provideBaseUrl(networkConfig: NetworkConfig): String {
        return networkConfig.baseUrl()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(networkConfig: NetworkConfig): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor(isDev = networkConfig.isDev())
    }

    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(): HttpRequestInterceptor {
        return createHttpRequestInterceptor()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return createChuckInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        httpRequestInterceptor: HttpRequestInterceptor
    ): OkHttpClient {
        return createOkHttpClient(
            isCache = true,
            interceptors = arrayOf(
                httpLoggingInterceptor,
                chuckerInterceptor,
                httpRequestInterceptor
            ),
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideCharacterService(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): CharacterService {
        return createRetrofitWithMoshi(
            okHttpClient = okHttpClient,
            moshi = moshi,
            baseUrl = baseUrl
        )
    }

    @Provides
    @Singleton
    fun provideEpisodeService(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ) = createRetrofitWithMoshi<EpisodeService>(
        okHttpClient = okHttpClient,
        moshi = moshi,
        baseUrl = baseUrl
    )

    @Provides
    @Singleton
    fun provideLocationService(
        @Named(value = BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ) = createRetrofitWithMoshi<LocationService>(
        okHttpClient = okHttpClient,
        moshi = moshi,
        baseUrl = baseUrl
    )
}