package ysuzuki.githubclient.ui.search

import androidx.databinding.ObservableField
import android.net.Uri
import ysuzuki.githubclient.model.Repo
import androidx.browser.customtabs.CustomTabsIntent
import android.view.View


/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchItemViewModel(repo: Repo) {

    val title: ObservableField<String> = ObservableField()
    val description: ObservableField<String> = ObservableField()
    val avatar: ObservableField<String> = ObservableField()
    val language: ObservableField<String> = ObservableField()
    val url: ObservableField<String> = ObservableField()
    val stars: ObservableField<String> = ObservableField()

    init {
        title.set(repo.name)
        description.set(repo.description)
        avatar.set(repo.owner.avatar_url)
        language.set(repo.language)
        url.set(repo.url)
        stars.set(repo.starts.toString())
    }

    fun openLink(v: View) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(v.context, Uri.parse(url.get()))
    }

}