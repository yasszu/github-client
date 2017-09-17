package ysuzuki.githubclient.data.remote

import io.reactivex.Single
import ysuzuki.githubclient.api.GitHubClient
import ysuzuki.githubclient.api.service.GitHubService
import ysuzuki.githubclient.model.Repository

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
object OrganizationReposDataSource {

    private val service: GitHubService = GitHubClient.service

    fun request(organization: String, page: Int, limit: Int): Single<List<Repository>>
            = service.getRepos(organization, page, limit)

}