package ysuzuki.githubclient.di

import dagger.Component
import ysuzuki.githubclient.ui.search.SearchFragment
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: SearchFragment)

}