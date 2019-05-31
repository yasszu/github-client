package ysuzuki.githubclient.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ysuzuki.githubclient.data.QueryRepository
import ysuzuki.githubclient.data.TrendingReposRepository
import javax.inject.Inject

class SearchViewModelFactory @Inject constructor(
        private val trendingReposRepository: TrendingReposRepository,
        private val queryRepository: QueryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            try {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(trendingReposRepository, queryRepository) as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}