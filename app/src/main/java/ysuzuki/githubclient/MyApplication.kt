package ysuzuki.githubclient

import android.app.Application
import ysuzuki.githubclient.util.SharedPreference

/**
 * Created by Yasuhiro Suzuki on 2017/04/22.
 */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreference.init(this)
    }

}