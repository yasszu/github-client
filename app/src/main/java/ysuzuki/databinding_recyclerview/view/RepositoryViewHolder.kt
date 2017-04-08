package ysuzuki.databinding_recyclerview.view

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ysuzuki.databinding_recyclerview.databinding.ItemRepositoryBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class RepositoryViewHolder(val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: RepositoryViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
        loadImage(binding.thumbnail, viewModel.avatar.get())
    }

    fun loadImage(view: ImageView, url: String) {
        Picasso.with(view.context)
                .load(url)
                .into(view)
    }

}