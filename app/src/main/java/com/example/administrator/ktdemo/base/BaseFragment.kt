package com.example.administrator.ktdemo.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
abstract class BaseFragment : RxFragment() {
    var misCreateView: Boolean = false
    var isLoadData: Boolean = false
    abstract fun getLayoutId(): Int

    abstract fun initViews(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        misCreateView = true
        initViews(savedInstanceState)
        loadData()
    }


    fun toast(str: String) {
        if (TextUtils.isEmpty(str)) {
            return
        }

        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        loadData()
    }

    private fun loadData() {
        if (userVisibleHint && misCreateView && !isLoadData) {
            isLoadData = true
            lazyLoad()
        }
    }

    abstract fun lazyLoad()

}