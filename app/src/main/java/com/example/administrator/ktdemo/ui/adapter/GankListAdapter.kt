package com.example.administrator.ktdemo.ui.adapter

import android.support.v4.content.ContextCompat
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.entity.GankBean
import com.example.administrator.ktdemo.util.GlideUtil
import com.example.administrator.ktdemo.util.RandomUtil
import kotlinx.android.synthetic.main.item_gank_list.view.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankListAdapter : BaseQuickAdapter<GankBean, BaseViewHolder> {

    constructor(data: MutableList<GankBean>?) : super(R.layout.item_gank_list, data)

    override fun convert(helper: BaseViewHolder?, item: GankBean?) {
        val view: View = helper!!.itemView

        if (item!!.images != null && item.images!!.size != 0) {
            view.card_img.visibility = View.VISIBLE
            GlideUtil.load(mContext, item.images!![0], view.img)
        } else {
            view.card_img.visibility = View.GONE
        }

        view.card_lable.setCardBackgroundColor(ContextCompat.getColor(mContext, RandomUtil.getColor()))
        view.tv_title.text = item.desc
        view.tv_lable.text = item.who
        view.tv_date.text = item.publishedAt
    }


}