package ysuzuki.githubclient.ui.search

import androidx.databinding.ObservableField
import ysuzuki.githubclient.model.Repo


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

}