package com.x_meteor.meteor.report.contract

import com.x_meteor.meteor.base.IBaseView
import com.x_meteor.meteor.base.IPresenter
import com.x_meteor.meteor.report.model.bean.ZhihuDetailNote
import io.reactivex.Observable

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/15 16:40
 * @company:
 * @email: lx802315@163.com
 */
class ReportDetailContract {

    interface View : IBaseView {
        //这里添加视图层方法
        fun setReportDetail(detail: ZhihuDetailNote)

    }

    interface Presenter : IPresenter<View> {

        //这里添加逻辑层方法
        fun getReportDetail(reportId: String)

    }

    interface ReportDetailModel {

        //这里添加逻辑层方法
        fun getReportDetail(reportId: String): Observable<ZhihuDetailNote>
    }
}