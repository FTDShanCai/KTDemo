package com.example.administrator.ktdemo.ui.fragment.gank

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseFragment
import com.example.administrator.ktdemo.base.RxSchedulers
import com.example.administrator.ktdemo.entity.GankBaseResult
import com.example.administrator.ktdemo.entity.GankBean
import com.example.administrator.ktdemo.net.GankObservable
import com.example.administrator.ktdemo.net.RetrofitUtil
import com.example.administrator.ktdemo.ui.adapter.GankFuliAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.trello.rxlifecycle2.android.FragmentEvent
import kotlinx.android.synthetic.main.fragment_gank_fuli.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankFuliFragment : BaseFragment() {

    private val list = arrayListOf<GankBean>()
    private lateinit var adapter: GankFuliAdapter
    private var page = 1

    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_fuli
    }

    override fun initViews(savedInstanceState: Bundle?) {

        adapter = GankFuliAdapter(list)
        recycler_view.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = adapter
        refresh_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout?) {
                getFuli(false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout?) {
                getFuli(true)
            }
        })
    }

    override fun lazyLoad() {
        refresh_layout.autoRefresh()
    }

    private fun getFuli(boolean: Boolean) {
        if (boolean) {
            page = 1
        }

        RetrofitUtil.instance.getGankApi().getListData("福利", page.toString())
                .compose(RxSchedulers.compose())
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(object : GankObservable<GankBaseResult<ArrayList<GankBean>>>() {
                    override fun OnSuccess(t: GankBaseResult<ArrayList<GankBean>>) {
                        onCompleteRefresh(boolean)

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
                        onCompleteRefresh(boolean)
                        toast(text)
                    }
                })
    }

    private fun onCompleteRefresh(boolean: Boolean) {
        if (boolean) {
            refresh_layout.finishRefresh()
        } else {
            refresh_layout.finishLoadMore()
        }
    }

}