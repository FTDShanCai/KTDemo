package com.example.administrator.ktdemo.entity

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class TodayBean {

    constructor(title: String?, list: ArrayList<GankBean>?) {
        this.title = title
        this.list = list
    }

    var title: String? = null
    var list: ArrayList<GankBean>? = null
}