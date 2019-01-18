package com.example.administrator.ktdemo.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class HomePagerAdapter : FragmentStatePagerAdapter {
    private lateinit var fragments: ArrayList<Fragment>

    private lateinit var titles: Array<String>

    constructor(fm: FragmentManager?, fragments: ArrayList<Fragment>, titles: Array<String>) : super(fm) {
        this.fragments = fragments
        this.titles = titles
    }


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}