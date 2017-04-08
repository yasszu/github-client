package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ysuzuki.databinding_recyclerview.databinding.ItemRepositoryBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class RepositoriesViewAdapter(val repositories: RepositoriesViewModel):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        repositories.viewModels.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<RepositoryViewModel>>() {
            override fun onChanged(contributorViewModels: ObservableList<RepositoryViewModel>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(contributorViewModels: ObservableList<RepositoryViewModel>, i: Int, i1: Int) {
                notifyItemRangeChanged(i, i1)
            }

            override fun onItemRangeInserted(contributorViewModels: ObservableList<RepositoryViewModel>, i: Int, i1: Int) {
                notifyItemRangeInserted(i, i1)
            }

            override fun onItemRangeMoved(contributorViewModels: ObservableList<RepositoryViewModel>, i: Int, i1: Int, i2: Int) {
                notifyItemMoved(i, i1)
            }

            override fun onItemRangeRemoved(contributorViewModels: ObservableList<RepositoryViewModel>, i: Int, i1: Int) {
                notifyItemRangeRemoved(i, i1)
            }
        })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewModel = repositories.viewModels[position]
        val viewHolder = holder as RepositoryViewHolder
        viewHolder.bind(viewModel)
    }

    override fun getItemCount(): Int = repositories.viewModels.size


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }
}