package ysuzuki.githubclient.data.local

import ysuzuki.githubclient.service.SharedPreference

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
object QualifiersDataSource {

    private val preferences = SharedPreference.preferences

    private val QUALIFIERS = "qualifiers"

    fun find(): String = preferences.getString(QUALIFIERS, "Android")

    fun update(q: String) {
        preferences.edit().putString(QUALIFIERS, q).apply()
    }

    fun delete() {
        preferences.edit().remove(QUALIFIERS).apply()
    }

}