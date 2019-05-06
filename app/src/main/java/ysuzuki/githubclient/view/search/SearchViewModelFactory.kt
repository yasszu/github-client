package ysuzuki.githubclient.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ysuzuki.githubclient.data.QueriesRepository
import ysuzuki.githubclient.data.TrendingReposRepository
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
        val trendingReposRepository: TrendingReposRepository,
        val queriesRepository: QueriesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            try {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(trendingReposRepository, queriesRepository) as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}