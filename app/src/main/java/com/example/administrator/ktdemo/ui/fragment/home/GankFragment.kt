package com.example.administrator.ktdemo.ui.fragment.home

import android.os.Bundle
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_gank_list.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankFragment : BaseFragment() {

    private lateinit var type: String

    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_list
    }

    override fun initViews(savedInstanceState: Bundle?) {
        type = arguments!!.getString("type")!!

    }

    companion object {
        fun newInstance(type: String): GankFragment {
            val bundle = Bundle()
            bundle.putString("type", type)
            val fragment = GankFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun lazyLoad() {

    }


}