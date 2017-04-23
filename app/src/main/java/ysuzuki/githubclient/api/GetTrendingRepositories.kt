package ysuzuki.githubclient.api

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ysuzuki.githubclient.BuildConfig
import ysuzuki.githubclient.model.SearchResult

/**
 * Created by Yasuhiro Suzuki on 2017/04/08.
 *
 * See https://developer.github.com/v3/search/#search-search
 */
object GetTrendingRepositories {

    val SORT = "stars"
    val ORDER = "desc"
    val LIMIT = 30

    val SERVICE: GitHubClientService = Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GitHubClientService::class.java)

    fun request(qualifiers: String, page: Int): Single<SearchResult>
            = SERVICE.getTrending(qualifiers, SORT, ORDER, page, LIMIT)
}