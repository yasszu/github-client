package ysuzuki.databinding_recyclerview

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import ysuzuki.databinding_recyclerview.databinding.ActivityMainBinding
import ysuzuki.databinding_recyclerview.view.ProjectsFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initToolbar()
        addFragment(ProjectsFragment.newInstance(), ProjectsFragment.TAG)
    }

    fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit()
    }

}
