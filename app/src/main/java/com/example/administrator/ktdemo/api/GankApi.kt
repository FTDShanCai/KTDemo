package com.example.administrator.ktdemo.api

import com.example.administrator.ktdemo.entity.GankBaseResult
import com.example.administrator.ktdemo.entity.GankBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
interface GankApi {

    @GET("random/data/{type}/20")
    fun getRandomData(@Path("type") type: String): Observable<GankBaseResult<ArrayList<GankBean>>>


}