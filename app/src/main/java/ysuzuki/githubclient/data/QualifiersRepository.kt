package ysuzuki.githubclient.data

import ysuzuki.githubclient.data.local.QualifiersDataSource

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
object QualifiersRepository {

    private val dataSource = QualifiersDataSource

    fun find(): String = dataSource.find()

    fun save(q: String) = dataSource.update(q)

    fun clear() = dataSource.delete()

}