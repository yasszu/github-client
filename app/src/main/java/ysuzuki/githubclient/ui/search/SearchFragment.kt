package ysuzuki.githubclient.ui.search

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ysuzuki.githubclient.MyApplication
import ysuzuki.githubclient.R
import ysuzuki.githubclient.databinding.FragmentSearchBinding
import ysuzuki.githubclient.util.OnScrollListener
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 *
 */
class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: SearchViewModelFactory

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this, viewModelProvider).get(SearchViewModel::class.java)
    }

    private lateinit var binding: FragmentSearchBinding

    private lateinit var searchView: SearchView

    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context) }

    private val scrollListener: OnScrollListener by lazy {
        OnScrollListener(layoutManager) {
            viewModel.fetchNextItems()
        }
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(s: String): Boolean {
            if (!s.isBlank()) {
                scrollListener.clear()
                viewModel.setQuery(s)
            }
            return false
        }

        override fun onQueryTextChange(s: String): Boolean = false
    }

    private val adapter: SearchItemAdapter by lazy {
        SearchItemAdapter { item -> openLink(item) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (context?.applicationContext as MyApplication).appComponent.inject(this)
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        initViewModel()
        initRecyclerView()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        requireActivity().menuInflater.inflate(R.menu.main, menu)
        searchView = (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setOnQueryTextListener(queryTextListener)
            setIconifiedByDefault(true)
            queryHint = resources.getString(R.string.search_hint)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> refreshItems()
        else -> true
    }

    private fun refreshItems(): Boolean {
        scrollListener.clear()
        viewModel.refreshItems()
        return true
    }

    private fun initViewModel() {
        viewModel.repos.observe(viewLifecycleOwner, Observer { items ->
            items?.let {
                adapter.submitList(it)
            }
        })
        viewModel.query.observe(viewLifecycleOwner, Observer { query ->
            activity?.title = query
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addOnScrollListener(scrollListener)
    }

    private fun openLink(item: SearchItemViewModel) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(item.url.get()))
    }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName
        fun newInstance() = SearchFragment()
    }

}