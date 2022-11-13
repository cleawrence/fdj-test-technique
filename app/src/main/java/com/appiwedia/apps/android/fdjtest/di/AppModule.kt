package com.appiwedia.apps.android.fdjtest.di

import android.app.Application
import com.appiwedia.apps.android.fdjtest.data.remote.TeamServiceApi
import com.appiwedia.apps.android.fdjtest.data.repositories.DefaultRepository
import com.appiwedia.apps.android.fdjtest.data.repositories.Repository
import com.appiwedia.apps.android.fdjtest.utils.Constants.BASE_URL
import com.appiwedia.apps.android.fdjtest.utils.DispatcherProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        cache: Cache,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        converterFactory: MoshiConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideLeaguesApi(retrofit: Retrofit): TeamServiceApi {
        return retrofit.create(TeamServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun provideMainRepository(
        teamServiceApi: TeamServiceApi,
        dispatcherProvider: DispatcherProvider,
    ): Repository = DefaultRepository(teamServiceApi, dispatcherProvider)
}