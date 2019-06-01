package ysuzuki.githubclient.ui.search

import androidx.lifecycle.*
import ysuzuki.githubclient.data.QueryRepository
import ysuzuki.githubclient.data.TrendingReposRepository
import ysuzuki.githubclient.model.Repo

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewModel constructor(
        private val trendingReposRepository: TrendingReposRepository,
        private val queryRepository: QueryRepository) : ViewModel() {

    private var page = 1

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    private val _repos = MutableLiveData<List<SearchItemViewModel>>()
    val repos: LiveData<List<SearchItemViewModel>> = Transformations
            .switchMap(_query) { query ->
                fetch(query, page)
            }

    init {
        setQuery(queryRepository.find())
    }

    fun setQuery(query: String) {
        page = 1
        queryRepository.save(query)
        _repos.value = emptyList()
        _query.value = query
    }

    fun fetchNextItems() {
        page++
        _query.value?.let { _query.value = it }
    }

    fun refreshItems() {
        queryRepository.clear()
        setQuery(queryRepository.find())
    }

    private fun fetch(query: String, page: Int): LiveData<List<SearchItemViewModel>> {
        _progress.value = true
        return trendingReposRepository
                .find(query, page)
                .map { result ->
                    val current = _repos.value ?: emptyList()
                    val latest = toItems(result.items)
                    val newValue = combine(current, latest)
                    _repos.value = newValue
                    _progress.value = false
                    newValue
                }
                .toFlowable()
                .toLiveData()
    }

    private fun <T> combine(current: List<T>, latest: List<T>): List<T> {
        return mutableListOf<T>().apply {
            addAll(current)
            addAll(latest)
        }
    }

    private fun toItems(repos: List<Repo>): List<SearchItemViewModel> {
        return repos.map { repo -> SearchItemViewModel(repo) }
    }

}