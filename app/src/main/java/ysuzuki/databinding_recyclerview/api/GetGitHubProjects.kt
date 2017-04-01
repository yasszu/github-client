package ysuzuki.databinding_recyclerview.api

import ysuzuki.databinding_recyclerview.model.Project
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ysuzuki.databinding_recyclerview.BuildConfig

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
object GetGitHubProjects {

    val LIMIT = 10

    val SERVICE: GetGitHubProjectsService = Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GetGitHubProjectsService::class.java)

    fun request(organization: String, page: Int): Single<List<Project>>
            = SERVICE.getProjects(organization, page, LIMIT)

}