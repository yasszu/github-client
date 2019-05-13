package ysuzuki.githubclient.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ysuzuki.githubclient.BuildConfig
import ysuzuki.githubclient.api.service.GitHubService
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 *
 * Context dependency
 */
@Module
class AppModule(val context: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGitHubService(): GitHubService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GitHubService::class.java)
    }

}