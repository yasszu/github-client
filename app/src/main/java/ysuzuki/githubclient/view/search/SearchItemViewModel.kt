package ysuzuki.githubclient.view.search

import androidx.databinding.ObservableField
import android.net.Uri
import ysuzuki.githubclient.model.Repository
import androidx.browser.customtabs.CustomTabsIntent
import android.view.View


/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchItemViewModel(repository: Repository) {

    val title: ObservableField<String> = ObservableField()
    val description: ObservableField<String> = ObservableField()
    val avatar: ObservableField<String> = ObservableField()
    val language: ObservableField<String> = ObservableField()
    val url: ObservableField<String> = ObservableField()
    val stars: ObservableField<String> = ObservableField()

    init {
        title.set(repository.name)
        description.set(repository.description)
        avatar.set(repository.owner.avatar_url)
        language.set(repository.language)
        url.set(repository.url)
        stars.set(repository.starts.toString())
    }

    fun openLink(v: View) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(v.context, Uri.parse(url.get()))
    }

}