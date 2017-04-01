package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableField
import android.net.Uri
import ysuzuki.databinding_recyclerview.model.Project
import android.support.customtabs.CustomTabsIntent
import android.view.View


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

    fun openLink(v: View) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(v.context, Uri.parse(url.get()))
    }

}