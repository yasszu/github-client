package ysuzuki.githubclient.view.search

import android.support.v7.widget.RecyclerView
import ysuzuki.githubclient.databinding.ItemRepositoryBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchItemViewHolder(val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: SearchItemViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

}