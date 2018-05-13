package ysuzuki.githubclient.view.search

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ysuzuki.githubclient.databinding.ItemRepositoryBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewAdapter(val viewModel: SearchViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        viewModel.addListChangeCallback(object : ObservableList.OnListChangedCallback<ObservableList<SearchItemViewModel>>() {
            override fun onChanged(contributorViewModels: ObservableList<SearchItemViewModel>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(contributorViewModels: ObservableList<SearchItemViewModel>, i: Int, i1: Int) {
                notifyItemRangeChanged(i, i1)
            }

            override fun onItemRangeInserted(contributorViewModels: ObservableList<SearchItemViewModel>, i: Int, i1: Int) {
                notifyItemRangeInserted(i, i1)
            }

            override fun onItemRangeMoved(contributorViewModels: ObservableList<SearchItemViewModel>, i: Int, i1: Int, i2: Int) {
                notifyItemMoved(i, i1)
            }

            override fun onItemRangeRemoved(contributorViewModels: ObservableList<SearchItemViewModel>, i: Int, i1: Int) {
                notifyItemRangeRemoved(i, i1)
            }
        })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewModel = viewModel.items[position]
        val viewHolder = holder as SearchItemViewHolder
        viewHolder.bind(viewModel)
    }

    override fun getItemCount(): Int = viewModel.items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return SearchItemViewHolder(binding)
    }

    class SearchItemViewHolder(val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: SearchItemViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

    }

}

