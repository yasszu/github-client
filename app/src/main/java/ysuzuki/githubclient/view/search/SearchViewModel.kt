package ysuzuki.githubclient.view.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import io.reactivex.disposables.CompositeDisposable
import ysuzuki.githubclient.data.QualifiersRepository
import ysuzuki.githubclient.model.Repository
import ysuzuki.githubclient.data.TrendingReposRepository
import ysuzuki.githubclient.util.OnScrollListener

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewModel(layoutManager: LinearLayoutManager, var listener: Listener?) {

    interface Listener {
        fun onFetchStart()
        fun onFetchComplete()
        fun onQueryTextSubmit()
    }

    private val disposables = CompositeDisposable()

    private val trendingReposRepository = TrendingReposRepository

    private val qualifiersRepository = QualifiersRepository

    val qualifiers: String
        get() = qualifiersRepository.find()

    var page = 0
        private set

    val items: ObservableList<SearchItemViewModel> = ObservableArrayList()

    val scrollListener: OnScrollListener = OnScrollListener(layoutManager, {
        requestRepositories(qualifiers, page++)
    })

    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String): Boolean {
            if (!s.isBlank()) {
                resetRepositories(s)
                listener?.onQueryTextSubmit()
            }
            return false
        }

        override fun onQueryTextChange(s: String): Boolean = false
    }

    fun fetch() {
        requestRepositories(qualifiers, page++)
    }

    private fun requestRepositories(qualifiers: String, page: Int) {
        listener?.onFetchStart()
        val disposable = trendingReposRepository
                .find(qualifiers, page)
                .subscribe({ (_, _, items) -> onFetchSuccess(items) }, this::onFetchError)
        disposables.add(disposable)
    }

    private fun onFetchSuccess(repositories: List<Repository>) {
        listener?.onFetchComplete()
        addViewModel(repositories)
    }

    private fun onFetchError(throwable: Throwable) {
        listener?.onFetchComplete()
        throwable.printStackTrace()
    }

    private fun addViewModel(repositories: List<Repository>) {
        repositories.forEach { items.add(SearchItemViewModel(it)) }
    }

    fun resetRepositories(qualifiers: String) {
        scrollListener.clear()
        items.clear()
        resetPage()
        qualifiersRepository.save(qualifiers)
        requestRepositories(qualifiers, page++)
    }

    fun refreshRepositories() {
        scrollListener.clear()
        items.clear()
        resetPage()
        qualifiersRepository.clear()
        requestRepositories(qualifiers, page++)
    }

    private fun resetPage() {
        page = 0
    }

    fun destroy() {
        disposables.dispose()
        scrollListener.clear()
        listener = null
    }

}