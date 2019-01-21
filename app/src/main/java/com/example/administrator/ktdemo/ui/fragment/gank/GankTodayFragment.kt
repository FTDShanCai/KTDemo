package com.example.administrator.ktdemo.ui.fragment.gank

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.base.BaseFragment
import com.example.administrator.ktdemo.base.RxSchedulers
import com.example.administrator.ktdemo.entity.GankBaseResult
import com.example.administrator.ktdemo.entity.GankBean
import com.example.administrator.ktdemo.entity.GankToDayBean
import com.example.administrator.ktdemo.entity.TodayBean
import com.example.administrator.ktdemo.net.GankObservable
import com.example.administrator.ktdemo.net.RetrofitUtil
import com.example.administrator.ktdemo.ui.adapter.GankTodayAdapter
import com.trello.rxlifecycle2.android.FragmentEvent
import kotlinx.android.synthetic.main.fragment_today.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankTodayFragment : BaseFragment() {

    private val list: ArrayList<TodayBean> = arrayListOf()
    private lateinit var adapter: GankTodayAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_today
    }

    override fun initViews(savedInstanceState: Bundle?) {
        adapter = GankTodayAdapter(list)
        recycler_view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = adapter
    }

    override fun lazyLoad() {
        getToday()
    }

    private fun getToday() {

        RetrofitUtil.instance.getGankApi().getToday()
                .compose(RxSchedulers.compose())
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(object : GankObservable<GankBaseResult<GankToDayBean>>() {
                    override fun OnSuccess(t: GankBaseResult<GankToDayBean>) {
                        addList(t.results!!.Android)
                        addList(t.results!!.iOS)
                        addList(t.results!!.App)
                        addList(t.results!!.扩展资源)
                        addList(t.results!!.瞎推荐)
                        addList(t.results!!.休息视频)
                        adapter.notifyDataSetChanged()
                    }

                    override fun OnError(text: String) {
                        toast(text)
                    }
                })
    }

    private fun addList(beans: ArrayList<GankBean>?) {
        if (beans != null && beans.size != 0) {
            list.add(TodayBean(beans[0].type, beans))
        }
    }
}