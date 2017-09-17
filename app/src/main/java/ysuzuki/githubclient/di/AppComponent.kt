package ysuzuki.githubclient.di

import dagger.Component
import ysuzuki.githubclient.view.search.SearchFragment
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(fragment: SearchFragment)

}