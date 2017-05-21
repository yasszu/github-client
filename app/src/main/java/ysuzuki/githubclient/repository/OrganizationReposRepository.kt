package ysuzuki.githubclient.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.githubclient.api.GetOrganizationRepos
import ysuzuki.githubclient.model.Repository

/**
 * Created by Yasuhiro Suzuki on 2017/05/21.
 */
object OrganizationReposRepository {

    fun findAll(organization: String, page: Int): Single<List<Repository>> = GetOrganizationRepos
            .request(organization, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}