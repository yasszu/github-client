package ysuzuki.githubclient.api.service

import ysuzuki.githubclient.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ysuzuki.githubclient.model.SearchResult

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
interface GitHubService {

    @GET("orgs/{query}/repos")
    fun getRepos(
            @Path("query") organization: String,
            @Query("page") page: Int,
            @Query("per_page") limit: Int): Single<List<Repo>>

    @GET("search/repositories")
    fun getTrending(
            @Query("q") qualifiers: String,
            @Query("sort") sort: String,
            @Query("order") order: String,
            @Query("page") page: Int,
            @Query("limit") limit: Int): Single<SearchResult>
}