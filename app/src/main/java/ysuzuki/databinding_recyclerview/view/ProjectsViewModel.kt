package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.databinding_recyclerview.api.GetGitHubProjects
import ysuzuki.databinding_recyclerview.model.Project
import ysuzuki.databinding_recyclerview.util.OnScrollListener

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class ProjectsViewModel {

    var page = 0
        private set

    val viewModels: ObservableList<ProjectViewModel> = ObservableArrayList()

    fun onScrollListener(mLayoutManager: LinearLayoutManager): OnScrollListener {
        return OnScrollListener(mLayoutManager, { fetch() })
    }

    fun fetch() {
        GetGitHubProjects.request(page++)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({projects -> addViewModel(projects)}, Throwable::printStackTrace)

    }

    fun addViewModel(projects: List<Project>) {
        projects.forEach { viewModels.add(ProjectViewModel(it)) }
    }

}