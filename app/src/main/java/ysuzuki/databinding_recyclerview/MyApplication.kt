package ysuzuki.databinding_recyclerview

import android.app.Application
import ysuzuki.databinding_recyclerview.util.SharedPreference

/**
 * Created by Yasuhiro Suzuki on 2017/04/22.
 */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreference.init(this)
    }

}