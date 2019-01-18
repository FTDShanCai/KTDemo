package com.example.administrator.ktdemo.net

import com.example.administrator.ktdemo.api.GankApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
class RetrofitUtil {
    /**
     * Gank url
     */
    private val GANKURL: String = "http://gank.io/api/"

    companion object {
        val instance: RetrofitUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitUtil()
        }
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }


    private fun getGankApi(): GankApi {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(GANKURL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(GankApi)
    }


}