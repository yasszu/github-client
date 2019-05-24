package ysuzuki.githubclient.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ysuzuki.githubclient.R
import ysuzuki.githubclient.databinding.ActivityMainBinding
import ysuzuki.githubclient.extention.addFragment
import ysuzuki.githubclient.ui.search.SearchFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initToolbar()
        addFragment(SearchFragment.TAG, SearchFragment.newInstance())
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp)
    }

}
