package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableField
import ysuzuki.databinding_recyclerview.model.Project

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class ProjectViewModel(project: Project) {

    val title: ObservableField<String> = ObservableField()
    val description: ObservableField<String> = ObservableField()
    val avatar: ObservableField<String> = ObservableField()
    val language: ObservableField<String> = ObservableField()
    val url: ObservableField<String> = ObservableField()

    init {
        title.set(project.name)
        description.set(project.description)
        avatar.set(project.owner.avatar_url)
        language.set(project.language)
        url.set(project.url)
    }

}