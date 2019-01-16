package com.x_meteor.meteor.report.contract

import com.x_meteor.meteor.base.IBaseView
import com.x_meteor.meteor.base.IPresenter
import com.x_meteor.meteor.report.model.bean.ZhihuNote
import io.reactivex.Observable

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/11 14:12
 * @company:
 * @email: lx802315@163.com
 */
class ReportContract {

    interface View : IBaseView {

        //设置数据
        fun setReportList(netData : ZhihuNote)

        //显示错误信息
        fun showError()

    }

    interface Presenter : IPresenter<View> {

        //从网络获取日报数据
        fun getReportListForNet(date: String)

        //从本地获取日报数据
        fun getReportListForLocal()

        //存储数据到本地
        fun saveReportToLocal()

    }

    interface ReportModel {

        //从网络获取日报数据
        fun getReportListForNet(date: String): Observable<ZhihuNote>

        //从本地获取日报数据
        fun getReportListForLocal()

        //存储数据到本地
        fun saveReportToLocal()

    }
}