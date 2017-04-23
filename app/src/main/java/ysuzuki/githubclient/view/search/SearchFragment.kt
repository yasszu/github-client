package ysuzuki.githubclient.view.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import ysuzuki.githubclient.R
import ysuzuki.githubclient.databinding.FragmentProjectsBinding
import ysuzuki.githubclient.view.SearchViewModel
import ysuzuki.githubclient.view.search.SearchViewAdapter

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchFragment : Fragment() {

    lateinit var binding: FragmentProjectsBinding

    lateinit var searchView: SearchView

    lateinit var viewModel: SearchViewModel

    val adapter: SearchViewAdapter by lazy { SearchViewAdapter(viewModel) }

    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String): Boolean {
            if (!s.isNullOrBlank()) {
                viewModel.resetRepositories(s)
                activity.title = viewModel.qualifiers
            }
            return false
        }

        override fun onQueryTextChange(s: String): Boolean {
            return false
        }
    }

    companion object {
        val TAG = SearchFragment::class.java.simpleName!!
        fun newInstance() = SearchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProjectsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onDetach() {
        viewModel.dispose()
        super.onDetach()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity.menuInflater.inflate(R.menu.main, menu)
        val menuItem = menu?.findItem(R.id.search)
        searchView = (menuItem?.actionView as SearchView).apply {
            setOnQueryTextListener(queryTextListener)
            setIconifiedByDefault(true)
            queryHint = "Search"
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> onClickHome()
            else -> true
        }
    }

    fun onClickHome(): Boolean {
        viewModel.clearRepositories()
        activity.title = viewModel.qualifiers
        return true
    }

    fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        viewModel = SearchViewModel(layoutManager)
        activity.title = viewModel.qualifiers
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addOnScrollListener(viewModel.scrollListener)
    }

}