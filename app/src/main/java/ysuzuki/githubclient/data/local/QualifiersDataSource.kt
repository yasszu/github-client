package ysuzuki.githubclient.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
@Singleton
class QualifiersDataSource @Inject constructor(val preferences: SharedPreferences) {

    private val QUALIFIERS = "qualifiers"

    fun find(): String = preferences.getString(QUALIFIERS, "Android")

    fun update(q: String) {
        preferences.edit().putString(QUALIFIERS, q).apply()
    }

    fun delete() {
        preferences.edit().remove(QUALIFIERS).apply()
    }

}