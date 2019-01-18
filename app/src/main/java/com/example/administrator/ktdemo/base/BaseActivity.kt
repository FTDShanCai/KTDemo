package com.example.administrator.ktdemo.base

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
abstract class BaseActivity : RxAppCompatActivity() {

    abstract fun getLayoutId(): Int

    abstract fun initViews(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews(savedInstanceState)
    }


    fun toast(str: String) {
        if (TextUtils.isEmpty(str))
            return
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}