package ysuzuki.githubclient.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ysuzuki.githubclient.databinding.ItemRepositoryBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchViewAdapter(
        val viewModel: SearchViewModel,
        val callback: (SearchItemViewModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<SearchItemViewModel> = mutableListOf()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewModel = items[position]
        val viewHolder = holder as SearchItemViewHolder
        viewHolder.bind(viewModel)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener {
            binding.viewModel?.let { callback(it) }
        }
        return SearchItemViewHolder(binding)
    }

    fun setItems(items: List<SearchItemViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class SearchItemViewHolder(val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: SearchItemViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

    }

}

