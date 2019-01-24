package com.example.administrator.ktdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.entity.GankBean
import com.example.administrator.ktdemo.util.GlideUtil
import kotlinx.android.synthetic.main.item_gank_fuli.view.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankFuliAdapter : BaseQuickAdapter<GankBean, BaseViewHolder> {
    override fun convert(helper: BaseViewHolder?, item: GankBean?) {
        val view = helper!!.itemView
        GlideUtil.loadImg(mContext, item!!.url!!, view.img)
        view.tv_date.text = item.publishedAt
    }

    constructor(data: MutableList<GankBean>?) : super(R.layout.item_gank_fuli, data)
}