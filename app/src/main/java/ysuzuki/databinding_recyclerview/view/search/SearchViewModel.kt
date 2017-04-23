package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ysuzuki.databinding_recyclerview.api.GetTrendingRepositories
import ysuzuki.databinding_recyclerview.model.Repository
import ysuzuki.databinding_recyclerview.util.OnScrollListener
import ysuzuki.databinding_recyclerview.util.SharedPreference
import ysuzuki.databinding_recyclerview.view.search.SearchItemViewModel

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewModel(layoutManager: LinearLayoutManager) {

    val qualifiers: String get() = SharedPreference.getQualifiers()

    var page = 0
        private set

    val viewModels: ObservableList<SearchItemViewModel> = ObservableArrayList()

    val scrollListener: OnScrollListener = OnScrollListener(layoutManager, { fetch(qualifiers, page++) })

    val disposables = CompositeDisposable()

    init {
        fetch(qualifiers, page++)
    }

    fun fetch(qualifiers: String, page: Int) {
        disposables.add(GetTrendingRepositories
                .request(qualifiers, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (_, _, items) -> addViewModel(items)}, Throwable::printStackTrace))
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