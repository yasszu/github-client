package ysuzuki.databinding_recyclerview.view

import android.util.Log
import ysuzuki.databinding_recyclerview.api.GetGitHubProjects
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class ProjectsViewModel {

    init {
    }

    fun fetch(page: Int) {
        GetGitHubProjects.request(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { projects -> projects.forEach { (name) -> Log.d("name", name) }},
                        { throwable -> Log.e("GetGitHubProjects", throwable.message) })
    }
}