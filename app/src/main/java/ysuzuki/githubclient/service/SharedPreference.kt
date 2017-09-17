package ysuzuki.githubclient.service

import android.content.Context
import android.content.SharedPreferences
import ysuzuki.githubclient.BuildConfig


/**
 * Created by Yasuhiro Suzuki on 2017/04/01.
 */
object SharedPreference {

    lateinit var preferences: SharedPreferences

    private val name = BuildConfig.APPLICATION_ID

    fun init(context: Context) {
        preferences = context.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

}