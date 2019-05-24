package ysuzuki.githubclient.ui.search

import androidx.lifecycle.*
import ysuzuki.githubclient.data.QueryRepository
import ysuzuki.githubclient.data.TrendingReposRepository
import ysuzuki.githubclient.model.Repo

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewModel constructor(
        val trendingReposRepository: TrendingReposRepository,
        val queryRepository: QueryRepository) : ViewModel() {

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

    val items = MediatorLiveData<List<SearchItemViewModel>>().apply { value = emptyList() }

    init {
        setQuery(queryRepository.find())
        initItems()
    }

    private fun initItems() {
        items.addSource(repos) { latest ->
            items.value?.also { current ->
                items.value = combine(current, latest)
            }
        }
    }

    private fun combine(current: List<SearchItemViewModel>, latest: List<SearchItemViewModel>): List<SearchItemViewModel> {
        return mutableListOf<SearchItemViewModel>().apply {
            addAll(current)
            addAll(latest)
        }
    }

    fun setQuery(query: String) {
        page = 1
        queryRepository.save(query)
        items.value = emptyList()
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
                    _progress.value = false
                    toItems(result.items)
                }
                .toFlowable()
                .toLiveData()
    }

    private fun toItems(repos: List<Repo>): List<SearchItemViewModel> {
        return repos.map { repo -> SearchItemViewModel(repo) }
    }

}