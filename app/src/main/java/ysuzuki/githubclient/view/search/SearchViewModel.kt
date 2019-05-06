package ysuzuki.githubclient.view.search

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import ysuzuki.githubclient.data.QueriesRepository
import ysuzuki.githubclient.data.TrendingReposRepository
import ysuzuki.githubclient.model.Repository

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewModel constructor(
        val trendingReposRepository: TrendingReposRepository,
        val queriesRepository: QueriesRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private var page = 1

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    private val repos: LiveData<List<SearchItemViewModel>> = Transformations
            .switchMap(_query) { query ->
                fetch(query, page)
            }

    val items = MediatorLiveData<List<SearchItemViewModel>>().also {
        emptyList<SearchItemViewModel>()
    }

    init {
        setQuery(queriesRepository.find())

        items.addSource(repos) { latest ->
            items.value?.also { current ->
                items.value = combine(current, latest)
            }
        }
    }

    private fun combine(current: List<SearchItemViewModel>, latest: List<SearchItemViewModel>): List<SearchItemViewModel> {
        val result = mutableListOf<SearchItemViewModel>()
        current.forEach { item -> result.add(item) }
        latest.forEach { item -> result.add(item) }
        return result
    }

    fun setQuery(query: String) {
        page = 1
        queriesRepository.save(query)
        items.value = emptyList()
        _query.value = query
    }

    fun fetchNextItems() {
        page++
        _query.value?.let { _query.value = it }
    }

    fun refreshItems() {
        queriesRepository.clear()
        setQuery(queriesRepository.find())
    }

    private fun fetch(query: String, page: Int): LiveData<List<SearchItemViewModel>> {
        _progress.value = true
        return trendingReposRepository
                .find(query, page)
                .map { result ->
                    _progress.value = false
                    toItems(result.items)
                }
                .toFlowable()
                .toLiveData()
    }

    private fun toItems(repos: List<Repository>): List<SearchItemViewModel> {
        return repos.map { repo -> SearchItemViewModel(repo) }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}