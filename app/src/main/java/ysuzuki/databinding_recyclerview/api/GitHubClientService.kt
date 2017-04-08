package ysuzuki.databinding_recyclerview.api

import ysuzuki.databinding_recyclerview.model.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ysuzuki.databinding_recyclerview.model.SearchResult

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
interface GitHubClientService {

    @GET("orgs/{qualifiers}/repos")
    fun getProjects(@Path("qualifiers") organization: String,
                    @Query("page") page: Int,
                    @Query("per_page") limit: Int): Single<List<Repository>>

    @GET("search/repositories")
    fun getTrendingRepos(
            @Query("q") qualifiers: String,
            @Query("sort") sort: String,
            @Query("order") order: String,
            @Query("page") page: Int,
            @Query("limit") limit: Int): Single<SearchResult>
}