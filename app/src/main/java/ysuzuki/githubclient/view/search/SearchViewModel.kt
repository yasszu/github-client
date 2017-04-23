package ysuzuki.githubclient.view

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ysuzuki.githubclient.api.GetTrendingRepositories
import ysuzuki.githubclient.model.Repository
import ysuzuki.githubclient.util.OnScrollListener
import ysuzuki.githubclient.util.SharedPreference
import ysuzuki.githubclient.view.search.SearchItemViewModel

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewModel(layoutManager: LinearLayoutManager) {

    var onFetchStart: () -> Unit = {}

    var onFetchComplete: () -> Unit = {}

    val qualifiers: String get() = SharedPreference.getQualifiers()

    var page = 0
        private set

    val viewModels: ObservableList<SearchItemViewModel> = ObservableArrayList()

    val scrollListener: OnScrollListener = OnScrollListener(
            layoutManager, { fetch(qualifiers, page++) }
    )

    val disposables = CompositeDisposable()

    init {
        fetch(qualifiers, page++)
    }

    constructor(layoutManager: LinearLayoutManager,
                onFetchStart: () -> Unit,
                onFetchComplete: () -> Unit) : this(layoutManager) {
        this.onFetchStart = onFetchStart
        this.onFetchComplete = onFetchComplete
    }

    fun fetch(qualifiers: String, page: Int) {
        onFetchStart()
        disposables.add(GetTrendingRepositories
                .request(qualifiers, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (_, _, items) -> onFetchSuccess(items) }, this::onFetchError))
    }

    fun onFetchSuccess(repositories: List<Repository>) {
        onFetchComplete()
        addViewModel(repositories)
    }

    fun onFetchError(throwable: Throwable) {
        onFetchComplete()
        throwable.printStackTrace()
    }

    fun addViewModel(repositories: List<Repository>) {
        repositories.forEach { viewModels.add(SearchItemViewModel(it)) }
    }

    fun resetRepositories(qualifiers: String) {
        SharedPreference.saveOrganization(qualifiers)
        scrollListener.clear()
        viewModels.clear()
        fetch(qualifiers, page++)
    }

    fun clearRepositories() {
        SharedPreference.clearQualifiers()
        scrollListener.clear()
        viewModels.clear()
        fetch(qualifiers, page++)
    }

    fun dispose() {
        disposables.dispose()
    }

}