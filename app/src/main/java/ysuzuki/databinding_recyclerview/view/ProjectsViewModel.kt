package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ysuzuki.databinding_recyclerview.api.GetGitHubProjects
import ysuzuki.databinding_recyclerview.model.Project

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class ProjectsViewModel {

    val viewModels: ObservableList<ProjectViewModel> = ObservableArrayList()

    fun fetch(page: Int) {
        GetGitHubProjects.request(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({projects -> addViewModel(projects)}, Throwable::printStackTrace)
    }

    fun addViewModel(projects: List<Project>) {
        projects.forEach { viewModels.add(ProjectViewModel(it)) }
    }

}