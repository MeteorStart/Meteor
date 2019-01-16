package com.hazz.kotlinmvp.api

import com.x_meteor.meteor.report.model.bean.ZhihuDetailNote
import com.x_meteor.meteor.report.model.bean.ZhihuNote
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by xuhao on 2017/11/16.
 * Api 接口
 */

interface ApiService {

    @GET("latest")
    fun getZhihuNotes(): Observable<ZhihuNote>

    @GET("before/{date}")
    fun getDateZhihuNotes(@Path("date") beforedate: String): Observable<ZhihuNote>

    @GET("{id}")
    fun getDetailZhhuNote(@Path("id") id: String): Observable<ZhihuDetailNote>
}