package ysuzuki.githubclient.data

import ysuzuki.githubclient.data.local.QualifiersDataSource
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
class QualifiersRepository @Inject constructor(val dataSource: QualifiersDataSource) {

    fun find(): String = dataSource.find()

    fun save(q: String) = dataSource.update(q)

    fun clear() = dataSource.delete()

}