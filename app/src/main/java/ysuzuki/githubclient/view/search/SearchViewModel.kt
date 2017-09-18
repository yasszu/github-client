package ysuzuki.githubclient.view.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import android.support.v7.widget.SearchView
import io.reactivex.disposables.CompositeDisposable
import ysuzuki.githubclient.data.QualifiersRepository
import ysuzuki.githubclient.data.TrendingReposRepository
import ysuzuki.githubclient.model.Repository
import ysuzuki.githubclient.model.SearchResult
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewModel @Inject constructor(
        val trendingReposRepository: TrendingReposRepository,
        val qualifiersRepository: QualifiersRepository) {

    private val disposables = CompositeDisposable()

    var onQueryTextSubmit: (q: String) -> Unit = {}

    val qualifiers: String
        get() = qualifiersRepository.find()

    var page = 0
        private set

    val items: ObservableList<SearchItemViewModel> = ObservableArrayList()

    val progress: ObservableField<Boolean> = ObservableField()

    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String): Boolean {
            if (!s.isBlank()) {
                resetItems(s)
                onQueryTextSubmit(s)
            }
            return false
        }

        override fun onQueryTextChange(s: String): Boolean = false
    }

    private var listChangeCallback: OnListChangedCallback<ObservableList<SearchItemViewModel>>? = null

    fun addListChangeCallback(callback: OnListChangedCallback<ObservableList<SearchItemViewModel>>) {
        listChangeCallback = callback
        items.addOnListChangedCallback(callback)
    }

    fun fetch() {
        requestItems(qualifiers, page++)
    }

    fun resetItems(qualifiers: String) {
        items.clear()
        resetPage()
        qualifiersRepository.save(qualifiers)
        requestItems(qualifiers, page++)
    }

    fun refreshItems() {
        items.clear()
        resetPage()
        qualifiersRepository.clear()
        requestItems(qualifiers, page++)
    }

    private fun requestItems(qualifiers: String, page: Int) {
        showProgressBar()
        val disposable = trendingReposRepository
                .find(qualifiers, page)
                .subscribe(this::onFetchSuccess, this::onFetchError)
        disposables.add(disposable)
    }

    private fun onFetchSuccess(result: SearchResult) {
        dismissProgressBar()
        addViewModel(result.items)
    }

    private fun onFetchError(throwable: Throwable) {
        dismissProgressBar()
        throwable.printStackTrace()
    }

    private fun addViewModel(repositories: List<Repository>) {
        repositories.forEach { items.add(SearchItemViewModel(it)) }
    }

    private fun showProgressBar() {
        progress.set(true)
    }

    private fun dismissProgressBar() {
        progress.set(false)
    }

    private fun resetPage() {
        page = 0
    }

    fun destroy() {
        disposables.clear()
        listChangeCallback?.also { items.removeOnListChangedCallback(it) }
    }

}