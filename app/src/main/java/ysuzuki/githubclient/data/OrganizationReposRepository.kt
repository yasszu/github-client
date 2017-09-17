package ysuzuki.githubclient.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.githubclient.data.remote.OrganizationReposDataSource
import ysuzuki.githubclient.model.Repository
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/05/21.
 */
class OrganizationReposRepository @Inject constructor(val dataSource: OrganizationReposDataSource) {

    private val LIMIT = 10

    fun find(organization: String, page: Int): Single<List<Repository>> = dataSource
            .request(organization, page, LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}