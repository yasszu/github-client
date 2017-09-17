package ysuzuki.githubclient.extention

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import ysuzuki.githubclient.R

/**
 * Created by Yasuhiro Suzuki on 2017/09/17.
 */

fun AppCompatActivity.addFragment(tag: String, fragment: Fragment) {
    if (supportFragmentManager.findFragmentByTag(tag) == null) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container,fragment, tag)
                .commit()
    }
}