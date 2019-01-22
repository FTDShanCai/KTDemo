package com.example.administrator.ktdemo.ui.fragment.gank

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseFragment
import com.example.administrator.ktdemo.base.RxSchedulers
import com.example.administrator.ktdemo.entity.GankBaseResult
import com.example.administrator.ktdemo.net.GankObservable
import com.example.administrator.ktdemo.net.RetrofitUtil
import com.example.administrator.ktdemo.ui.adapter.GankHisoryAdapter
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.trello.rxlifecycle2.android.FragmentEvent
import kotlinx.android.synthetic.main.fragment_gank_history.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankHistoryFragment : BaseFragment() {

    private val list = arrayListOf<String>()
    private lateinit var adapter: GankHisoryAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_history
    }

    override fun initViews(savedInstanceState: Bundle?) {
        adapter = GankHisoryAdapter(list)
        recycler_view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        refresh_layout.setOnRefreshListener(OnRefreshListener {
            getHistoryDay()
        })
        refresh_layout.isEnableLoadMore = false
        recycler_view.adapter = adapter
    }

    override fun lazyLoad() {
        refresh_layout.autoRefresh()
    }

    private fun getHistoryDay() {
        RetrofitUtil.instance.getGankApi().getHistory()
                .compose(RxSchedulers.compose())
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(object : GankObservable<GankBaseResult<ArrayList<String>>>() {
                    override fun OnSuccess(t: GankBaseResult<ArrayList<String>>) {
                        refresh_layout.finishRefresh()
                        list.clear()
                        list.addAll(t!!.results!!)
                        adapter.notifyDataSetChanged()
                    }

                    override fun OnError(text: String) {
                        refresh_layout.finishRefresh()
                        toast(text)
                    }
                })
    }
}