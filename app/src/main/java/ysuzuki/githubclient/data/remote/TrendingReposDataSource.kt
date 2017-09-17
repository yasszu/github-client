package ysuzuki.githubclient.data.remote

import io.reactivex.Single
import ysuzuki.githubclient.api.GitHubClient
import ysuzuki.githubclient.api.service.GitHubService
import ysuzuki.githubclient.model.SearchResult

/**
 * Created by Yasuhiro Suzuki on 2017/04/08.
 *
 * See https://developer.github.com/v3/search/#search-search
 */
object TrendingReposDataSource {

    private val SORT = "stars"
    private val ORDER = "desc"

    private val service: GitHubService = GitHubClient.service

    fun find(qualifiers: String, page: Int, limit: Int): Single<SearchResult>
            = service.getTrending(qualifiers, SORT, ORDER, page, limit)

}