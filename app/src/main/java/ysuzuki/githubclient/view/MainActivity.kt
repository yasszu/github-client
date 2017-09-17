package ysuzuki.githubclient.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ysuzuki.githubclient.R
import ysuzuki.githubclient.databinding.ActivityMainBinding
import ysuzuki.githubclient.view.search.SearchFragment
import ysuzuki.githubclient.extention.addFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initToolbar()
        addFragment(SearchFragment.TAG, SearchFragment.newInstance())
    }

    fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp)
    }

}
