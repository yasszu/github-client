package ysuzuki.databinding_recyclerview.api

import ysuzuki.databinding_recyclerview.model.Project
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
interface GetGitHubProjectsService {
    @GET("orgs/googlesamples/repos")
    fun getPrpjects(@Query("page") page: Int, @Query("per_page") limit: Int): Single<List<Project>>
}