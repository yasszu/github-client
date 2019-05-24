package ysuzuki.githubclient.data

import ysuzuki.githubclient.data.local.QueryDataSource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
@Singleton
class QueryRepository @Inject constructor(val dataSource: QueryDataSource) {

    fun find(): String = dataSource.find()

    fun save(q: String) = dataSource.update(q)

    fun clear() = dataSource.delete()

}