package ysuzuki.githubclient.view.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import ysuzuki.githubclient.R
import ysuzuki.githubclient.databinding.FragmentSearchBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 *
 */
class SearchFragment : Fragment(), SearchViewModel.Listener {

    lateinit var binding: FragmentSearchBinding

    lateinit var searchView: SearchView

    val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context) }

    val viewModel: SearchViewModel by lazy { SearchViewModel(layoutManager, this) }

    val adapter: SearchViewAdapter by lazy { SearchViewAdapter(viewModel) }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName
        fun newInstance() = SearchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
    }

    override fun onDetach() {
        viewModel.destroy()
        super.onDetach()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        activity.menuInflater.inflate(R.menu.main, menu)
        searchView = (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setOnQueryTextListener(viewModel.queryTextListener)
            setIconifiedByDefault(true)
            queryHint = resources.getString(R.string.search_hint)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> refreshItems()
        else -> true
    }

    override fun onFetchStart() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onFetchComplete() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onQueryTextSubmit() {
        activity.title = viewModel.qualifiers
    }

    fun refreshItems(): Boolean {
        viewModel.refreshItems()
        activity.title = viewModel.qualifiers
        return true
    }

    fun setupRecyclerView() {
        activity.title = viewModel.qualifiers
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addOnScrollListener(viewModel.scrollListener)
    }

}