package com.example.administrator.ktdemo.ui.fragment.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseFragment
import com.example.administrator.ktdemo.base.RxSchedulers
import com.example.administrator.ktdemo.entity.GankBaseResult
import com.example.administrator.ktdemo.entity.GankBean
import com.example.administrator.ktdemo.net.GankObservable
import com.example.administrator.ktdemo.net.RetrofitUtil
import com.example.administrator.ktdemo.ui.adapter.GankListAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.Observer
import kotlinx.android.synthetic.main.fragment_gank_list.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankFragment : BaseFragment() {

    private lateinit var type: String
    private var page: Int = 1
    private val list = ArrayList<GankBean>()
    private lateinit var adapter: GankListAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_list
    }

    override fun initViews(savedInstanceState: Bundle?) {
        type = arguments!!.getString("type")!!

        adapter = GankListAdapter(list)

        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout?) {
                getGankDatas(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout?) {
                getGankDatas(true)
            }
        })

        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = adapter
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
        refresh_layout.autoRefresh()
    }


    private fun getGankDatas(boolean: Boolean) {

        if (boolean) {
            page = 1
        }

        RetrofitUtil.instance.getGankApi().getListData(type, page.toString())
                .compose(RxSchedulers.compose())
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(object : GankObservable<GankBaseResult<ArrayList<GankBean>>>() {
                    override fun OnSuccess(t: GankBaseResult<ArrayList<GankBean>>) {
                        completeRefresh(boolean)

                        if (boolean) {
                            list.clear()
                        }

                        adapter.addData(t.results!!)

                        if (t.results!!.size == 0) {
                            refresh_layout.isEnableLoadMore = false
                        } else {
                            refresh_layout.isEnableLoadMore = true
                            page++
                        }
                    }

                    override fun OnError(text: String) {
                        completeRefresh(boolean)
                        toast(text)
                    }
                })

    }

    private fun completeRefresh(boolean: Boolean) {
        if (boolean) {
            refresh_layout.finishRefresh()
        } else {
            refresh_layout.finishLoadMore()
        }
    }

}