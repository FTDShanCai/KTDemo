package com.example.administrator.ktdemo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseFragment
import com.example.administrator.ktdemo.ui.fragment.gank.GankFuliFragment
import com.example.administrator.ktdemo.ui.fragment.gank.GankHistoryFragment
import com.example.administrator.ktdemo.ui.fragment.gank.GankTodayFragment
import com.example.administrator.ktdemo.ui.fragment.gank.HomeFragment
import kotlinx.android.synthetic.main.fragment_gank_io.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankIoFragment : BaseFragment() {

    private val homeFragment = HomeFragment()
    private val todayFragment = GankTodayFragment()
    private val historyFragment = GankHistoryFragment()
    private val fankFuliFragmnt = GankFuliFragment()

    private var currentFragment: Fragment? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_io
    }

    override fun initViews(savedInstanceState: Bundle?) {
        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    changeFragment(homeFragment)
                }
                R.id.menu_today -> {
                    changeFragment(todayFragment)
                }
                R.id.menu_history -> {
                    changeFragment(historyFragment)
                }
                R.id.menu_fu_li -> {
                    changeFragment(fankFuliFragmnt)
                }

            }
            true
        }
    }

    override fun lazyLoad() {
        changeFragment(homeFragment)
    }


    private fun changeFragment(fragment: Fragment) {
        if (fragment == currentFragment) return

        val transaction = fragmentManager!!.beginTransaction()

        if (currentFragment != null) {
            transaction.hide(currentFragment!!)
        }

        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(R.id.frame_gank, fragment)
        }
        transaction.commitAllowingStateLoss()
        currentFragment = fragment
    }




}


