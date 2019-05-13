package ysuzuki.githubclient.extention

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
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