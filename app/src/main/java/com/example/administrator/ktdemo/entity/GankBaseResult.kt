package com.example.administrator.ktdemo.entity

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GankBaseResult<T> {
    var error: Boolean? = false
    var results: T? = null
}