package com.example.administrator.ktdemo.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class GlideUtil {

    companion object {
        fun load(context: Context, url: String, imageView: ImageView) {
            Glide.with(context).load(url).into(imageView)
        }
    }
}