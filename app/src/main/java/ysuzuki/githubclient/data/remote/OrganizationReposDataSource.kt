package ysuzuki.githubclient.data.remote

import io.reactivex.Single
import ysuzuki.githubclient.api.service.GitHubService
import ysuzuki.githubclient.model.Repository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
@Singleton
class OrganizationReposDataSource @Inject constructor(val service: GitHubService) {

    fun request(organization: String, page: Int, limit: Int): Single<List<Repository>>
            = service.getRepos(organization, page, limit)

}