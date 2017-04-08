package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.databinding_recyclerview.api.GetTrendingRepos
import ysuzuki.databinding_recyclerview.model.Project
import ysuzuki.databinding_recyclerview.util.OnScrollListener
import ysuzuki.databinding_recyclerview.util.SharedPreference

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class ProjectsViewModel(layoutManager: LinearLayoutManager) {

    val qualifiers: String get() = SharedPreference.getQualifiers()

    var page = 0
        private set

    val viewModels: ObservableList<ProjectViewModel> = ObservableArrayList()

    val scrollListener: OnScrollListener = OnScrollListener(layoutManager, { fetch() })

    init {
        fetch()
    }

    fun fetch() {
        GetTrendingRepos.request(qualifiers, page++)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (_, _, items) -> addViewModel(items)}, Throwable::printStackTrace)
        Log.d("page", page.toString())
    }

    fun addViewModel(projects: List<Project>) {
        projects.forEach { viewModels.add(ProjectViewModel(it)) }
    }

    fun resetRepositories(qualifiers: String) {
        SharedPreference.saveOrganization(qualifiers)
        scrollListener.clear()
        viewModels.clear()
        fetch()
    }

    fun clearRepositories(): Boolean {
        SharedPreference.clearQualifiers()
        scrollListener.clear()
        viewModels.clear()
        fetch()
        return true
    }

}