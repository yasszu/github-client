package ysuzuki.databinding_recyclerview.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ysuzuki.databinding_recyclerview.view.ProjectsViewModel
import ysuzuki.databinding_recyclerview.databinding.FragmentProjectsBinding

/**
 * Created by Yasuhiro Suzuki on 2017/03/30.
 */
class ProjectsFragment: Fragment() {

    lateinit var binding: FragmentProjectsBinding

    val viewModel: ProjectsViewModel by lazy { ProjectsViewModel() }

    companion object {
        val TAG = ProjectsFragment::class.java.simpleName!!
        fun newInstance() = ProjectsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity.title = "googlesamoles"
        binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch(1)
    }

}