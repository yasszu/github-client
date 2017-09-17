package ysuzuki.githubclient.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.githubclient.data.remote.TrendingReposDataSource
import ysuzuki.githubclient.model.SearchResult
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/05/21.
 */
class TrendingReposRepository @Inject constructor(val dataSource: TrendingReposDataSource) {

    private val LIMIT = 30

    fun find(qualifiers: String, page: Int): Single<SearchResult> = dataSource
            .find(qualifiers, page, LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}