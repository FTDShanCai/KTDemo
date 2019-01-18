package com.example.administrator.ktdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.ktdemo.R
import com.example.administrator.ktdemo.entity.GankBean

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankListAdapter : BaseQuickAdapter<GankBean, BaseViewHolder> {

    constructor(data: MutableList<GankBean>?) : super(R.layout.item_gank_list, data)

    override fun convert(helper: BaseViewHolder?, item: GankBean?) {

    }


}