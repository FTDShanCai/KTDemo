package com.example.administrator.ktdemo.ui.adapter

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.entity.TodayBean
import com.example.administrator.ktdemo.util.GlideUtil
import com.example.administrator.ktdemo.util.RandomUtil
import kotlinx.android.synthetic.main.item_gank_today.view.*
import kotlinx.android.synthetic.main.item_today.view.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankTodayAdapter : BaseQuickAdapter<TodayBean, BaseViewHolder> {

    constructor(data: MutableList<TodayBean>?) : super(R.layout.item_gank_today, data)

    override fun convert(helper: BaseViewHolder?, item: TodayBean?) {
        val view = helper!!.itemView
        view.ll_bg.setBackgroundColor(ContextCompat.getColor(mContext, RandomUtil.getColor()))
        view.tv_title.text = item!!.title
        view.ll_views.removeAllViews()
        for (bean in item.list!!) {
            val view_today = LayoutInflater.from(mContext).inflate(R.layout.item_today, null)
            if (bean.images != null && bean.images!!.size != 0) {
                GlideUtil.load(mContext, bean.images!![0], view_today.img)
            } else {
                view_today.img.setImageResource(RandomUtil.getColor())
            }
            view_today.tv_text.text = bean.desc
            view.ll_views.addView(view_today)
        }
    }

}