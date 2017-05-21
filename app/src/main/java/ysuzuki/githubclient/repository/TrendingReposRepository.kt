package ysuzuki.githubclient.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.githubclient.api.GetTrendingRepos
import ysuzuki.githubclient.model.SearchResult

/**
 * Created by Yasuhiro Suzuki on 2017/05/21.
 */
object TrendingReposRepository {

    fun findAll(qualifiers: String, page: Int): Single<SearchResult> = GetTrendingRepos
                .request(qualifiers, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

}