package ysuzuki.githubclient.data

import ysuzuki.githubclient.data.local.QueriesDataSource
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
class QueriesRepository @Inject constructor(val dataSource: QueriesDataSource) {

    fun find(): String = dataSource.find()

    fun save(q: String) = dataSource.update(q)

    fun clear() = dataSource.delete()

}