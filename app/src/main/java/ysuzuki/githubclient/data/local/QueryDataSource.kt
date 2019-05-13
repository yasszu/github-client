package ysuzuki.githubclient.data.local

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
class QueryDataSource @Inject constructor(val preferences: SharedPreferences) {

    private val QUERY: String = "query"
    private val DEFAULT: String = "Android"

    fun find(): String = preferences.getString(QUERY, DEFAULT) ?: DEFAULT

    fun update(q: String) = preferences.edit().putString(QUERY, q).apply()

    fun delete() = preferences.edit().remove(QUERY).apply()

}