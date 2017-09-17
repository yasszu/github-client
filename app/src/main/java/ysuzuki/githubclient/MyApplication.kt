package ysuzuki.githubclient

import android.app.Application
import ysuzuki.githubclient.di.AppComponent
import ysuzuki.githubclient.di.AppModule
import ysuzuki.githubclient.di.DaggerAppComponent

/**
 * Created by Yasuhiro Suzuki on 2017/04/22.
 */
class MyApplication: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}