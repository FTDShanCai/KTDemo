package com.example.administrator.ktdemo.ui.fragment.home

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseFragment
import com.example.administrator.ktdemo.ui.adapter.HomePagerAdapter
import com.example.administrator.ktdemo.ui.fragment.home.GankFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class HomeFragment : BaseFragment() {
    override fun lazyLoad() {

    }

    private lateinit var adapter: HomePagerAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initViews(savedInstanceState: Bundle?) {
        initHomes()
    }

    private fun initHomes() {
        val titles: Array<String> = resources.getStringArray(R.array.gank_types)
        val fragments: ArrayList<Fragment> = arrayListOf()

        for (title in titles) {
            fragments.add(GankFragment.newInstance(title))
        }

        adapter = HomePagerAdapter(childFragmentManager, fragments, titles)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 2
        tab_layout.setupWithViewPager(view_pager)
    }


}