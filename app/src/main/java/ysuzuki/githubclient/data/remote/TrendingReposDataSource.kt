package ysuzuki.githubclient.data.remote

import io.reactivex.Single
import ysuzuki.githubclient.api.service.GitHubService
import ysuzuki.githubclient.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/04/08.
 *
 * See https://developer.github.com/v3/search/#search-search
 */
class TrendingReposDataSource @Inject constructor(val service: GitHubService) {

    private val SORT = "stars"

    private val ORDER = "desc"

    fun find(query: String, page: Int, limit: Int): Single<SearchResult>
            = service.getTrending(query, SORT, ORDER, page, limit)

}