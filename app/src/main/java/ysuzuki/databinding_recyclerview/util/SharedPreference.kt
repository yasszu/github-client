package ysuzuki.databinding_recyclerview.util

import android.content.Context
import android.content.SharedPreferences
import ysuzuki.databinding_recyclerview.BuildConfig


/**
 * Created by Yasuhiro Suzuki on 2017/04/01.
 */
object SharedPreference {

    lateinit var data: SharedPreferences

    val name = BuildConfig.APPLICATION_ID
    val ORGANIZATION = "qualifiers"
    val TRENDING = "trending"
    val QUALIFIERS = "qualifiers"

    fun init(context: Context) {
        data = context.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun saveOrganization(organization: String) {
        data.edit().putString(ORGANIZATION, organization).apply()
    }

    fun getOrganization() = data.getString(ORGANIZATION, "googlesamples")!!

    fun saveQualifiers(q: String) {
        data.edit().putString(QUALIFIERS, q).apply()
    }

    fun getQualifiers() = data.getString(QUALIFIERS, "android+language:java")!!

    fun clearQualifiers() = data.edit().remove(QUALIFIERS).commit()

}