package com.example.administrator.ktdemo.api

import com.example.administrator.ktdemo.entity.GankBaseResult
import com.example.administrator.ktdemo.entity.GankBean
import com.example.administrator.ktdemo.entity.GankToDayBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
interface GankApi {

    //获取gank 随机数据
    @GET("random/data/{type}/20")
    fun getRandomData(@Path("type") type: String): Observable<GankBaseResult<ArrayList<GankBean>>>

    //获取指定类型的列表数据
    @GET("data/{type}/15/{page}")
    fun getListData(@Path("type") type: String, @Path("page") page: String): Observable<GankBaseResult<ArrayList<GankBean>>>

    //获取发过干货日期接口
    @GET("day/history")
    fun getHistory(): Observable<GankBaseResult<ArrayList<String>>>

    //获取最新一天的干货
    @GET("today")
    fun getToday(): Observable<GankBaseResult<GankToDayBean>>
}