package ysuzuki.databinding_recyclerview.view

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ysuzuki.databinding_recyclerview.databinding.ItemProjectBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class ProjectsViewAdapter(val projects: ProjectsViewModel):  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        projects.viewModels.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<ProjectViewModel>>() {
            override fun onChanged(contributorViewModels: ObservableList<ProjectViewModel>) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(contributorViewModels: ObservableList<ProjectViewModel>, i: Int, i1: Int) {
                notifyItemRangeChanged(i, i1)
            }

            override fun onItemRangeInserted(contributorViewModels: ObservableList<ProjectViewModel>, i: Int, i1: Int) {
                notifyItemRangeInserted(i, i1)
            }

            override fun onItemRangeMoved(contributorViewModels: ObservableList<ProjectViewModel>, i: Int, i1: Int, i2: Int) {
                notifyItemMoved(i, i1)
            }

            override fun onItemRangeRemoved(contributorViewModels: ObservableList<ProjectViewModel>, i: Int, i1: Int) {
                notifyItemRangeRemoved(i, i1)
            }
        })
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewModel = projects.viewModels[position]
        val viewHolder = holder as ProjectViewHolder
        viewHolder.bind(viewModel)
    }

    override fun getItemCount(): Int = projects.viewModels.size


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemProjectBinding.inflate(inflater, parent, false)
        return ProjectViewHolder(binding)
    }
}