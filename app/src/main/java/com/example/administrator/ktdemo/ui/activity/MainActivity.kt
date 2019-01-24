package com.example.administrator.ktdemo.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseActivity
import com.example.administrator.ktdemo.ui.fragment.GankIoFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_dr.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var currentFragment: Fragment? = null
    private val gankFragment: GankIoFragment = GankIoFragment()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews(savedInstanceState: Bundle?) {
        tool_bar.title = "首页"
        setSupportActionBar(tool_bar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, tool_bar, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigation.setNavigationItemSelectedListener(this)

        navigation.setCheckedItem(R.id.menu_home)
        changeFm(gankFragment)

        fab.setOnClickListener {
            showDiloag()
        }
    }

    private fun showDiloag() {
        val dialog = alert("message", "title") {
            okButton { toast("i am ok") }
            cancelButton { toast("cancel") }
        }

        dialog.show()
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when (menu.itemId) {
            R.id.menu_home -> {
                changeFm(gankFragment)
            }

            R.id.menu_fu_li -> {
                toast("福利")
            }

            R.id.menu_huang_li -> {
                toast("黄历")
            }

            R.id.menu_wan_android -> {
                toast("玩android")
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

    private fun changeFm(fragment: Fragment) {
        if (fragment == currentFragment)
            return


        if (currentFragment != null) {
            transaction.hide(currentFragment!!)
        }

        if (!fragment.isAdded) {
            transaction.add(R.id.frame_layout, fragment)
        } else {
            transaction.show(fragment)
        }
        transaction.commitAllowingStateLoss()
        currentFragment = fragment
    }
}
