package com.example.administrator.ktdemo.util

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.administrator.ktdemo.R

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

        fun loadImg(context: Context, url: String, imageView: ImageView) {
            val options = RequestOptions()
            options.placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.grey)))
            Glide.with(context).applyDefaultRequestOptions(options).load(url).into(imageView)
        }
    }
}