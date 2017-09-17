package ysuzuki.githubclient.util

import android.content.Context
import android.content.SharedPreferences
import ysuzuki.githubclient.BuildConfig


/**
 * Created by Yasuhiro Suzuki on 2017/04/01.
 */
object SharedPreference {

    lateinit var preferences: SharedPreferences

    val name = BuildConfig.APPLICATION_ID

    val ORGANIZATION = "organization"

    val TRENDING = "trending"

    val QUALIFIERS = "qualifiers"

    fun init(context: Context) {
        preferences = context.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun saveOrganization(organization: String) {
        preferences.edit().putString(ORGANIZATION, organization).apply()
    }

    fun getOrganization() = preferences.getString(ORGANIZATION, "googlesamples")!!

    fun saveQualifiers(q: String) {
        preferences.edit().putString(QUALIFIERS, q).apply()
    }

    fun getQualifiers() = preferences.getString(QUALIFIERS, "Android")!!

    fun clearQualifiers() = preferences.edit().remove(QUALIFIERS).apply()

}