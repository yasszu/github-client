package ysuzuki.githubclient.data.remote

import io.reactivex.Single
import ysuzuki.githubclient.api.service.GitHubService
import ysuzuki.githubclient.model.Repo
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class OrganizationReposDataSource @Inject constructor(val service: GitHubService) {

    fun request(organization: String, page: Int, limit: Int): Single<List<Repo>>
            = service.getRepos(organization, page, limit)

}