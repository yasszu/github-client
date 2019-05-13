package ysuzuki.githubclient.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.githubclient.data.remote.TrendingReposDataSource
import ysuzuki.githubclient.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/05/21.
 */
@Singleton
class TrendingReposRepository @Inject constructor(val dataSource: TrendingReposDataSource) {

    private val LIMIT = 10

    fun find(query: String, page: Int): Single<SearchResult> = dataSource
            .find(query, page, LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}