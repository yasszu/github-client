package ysuzuki.databinding_recyclerview.view

import android.support.v7.widget.RecyclerView
import ysuzuki.databinding_recyclerview.databinding.ItemRepositoryBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class SearchItemViewHolder(val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: SearchItemViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

}