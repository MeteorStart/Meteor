package com.x_meteor.meteor.report.model

import com.hazz.kotlinmvp.api.UrlConstant
import com.hazz.kotlinmvp.net.RetrofitManager
import com.x_meteor.kotlindemo.rx.scheduler.SchedulerUtils
import com.x_meteor.meteor.report.contract.ReportContract
import com.x_meteor.meteor.report.model.bean.ZhihuNote
import io.reactivex.Observable

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/14 15:14
 * @company:
 * @email: lx802315@163.com
 */
class ReportModel : ReportContract.ReportModel {

    override fun getReportListForNet(date: String): Observable<ZhihuNote> {
        RetrofitManager.baseUrl = UrlConstant.ZHIHU_BASE_URL
        if (date == "") {
            return RetrofitManager.service.getZhihuNotes()
                .compose(SchedulerUtils.ioToMain())
        } else {
            return RetrofitManager.service.getDateZhihuNotes(date)
                .compose(SchedulerUtils.ioToMain())
        }
    }

    override fun getReportListForLocal() {
    }

    override fun saveReportToLocal() {
    }

}