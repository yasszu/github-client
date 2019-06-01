package ysuzuki.githubclient.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ysuzuki.githubclient.databinding.RepoItemBinding

/**
 * Created by Yasuhiro Suzuki on 2019-05-31.
 */
class SearchItemAdapter(
        private val callback: (SearchItemViewModel) -> Unit
) : ListAdapter<SearchItemViewModel, ItemViewHolder>(
        object : DiffUtil.ItemCallback<SearchItemViewModel>() {
            override fun areItemsTheSame(oldItem: SearchItemViewModel, newItem: SearchItemViewModel): Boolean {
                return oldItem.repo.full_name == newItem.repo.full_name
            }

            override fun areContentsTheSame(oldItem: SearchItemViewModel, newItem: SearchItemViewModel): Boolean {
                return oldItem.repo.description == newItem.repo.description
                        && oldItem.repo.starts == newItem.repo.starts
            }
        }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )

        binding.root.setOnClickListener {
            binding.viewModel?.let {
                callback(it)
            }
        }
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ItemViewHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: SearchItemViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

}