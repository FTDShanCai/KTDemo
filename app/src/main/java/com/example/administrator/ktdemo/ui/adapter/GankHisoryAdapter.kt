package com.example.administrator.ktdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.ktdemo.R
import kotlinx.android.synthetic.main.item_history_day.view.*

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankHisoryAdapter : BaseQuickAdapter<String, BaseViewHolder> {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        val view = helper!!.itemView
        view.tv_date.text = item
    }

    constructor(data: MutableList<String>?) : super(R.layout.item_history_day, data)
}