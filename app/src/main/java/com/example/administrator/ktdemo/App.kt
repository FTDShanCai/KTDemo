package com.example.administrator.ktdemo

import android.app.Application
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initSmartRefresh()
    }

    companion object {

        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }

    private fun initSmartRefresh() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
            ClassicsHeader(context)
        }
    }

}