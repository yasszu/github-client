package ysuzuki.githubclient.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ysuzuki.githubclient.BuildConfig
import ysuzuki.githubclient.api.service.GitHubService

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
object GitHubClient {

    val service: GitHubService = Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GitHubService::class.java)

}