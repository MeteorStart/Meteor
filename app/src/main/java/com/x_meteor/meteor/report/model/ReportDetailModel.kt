package com.x_meteor.meteor.report.model

import com.hazz.kotlinmvp.api.UrlConstant
import com.hazz.kotlinmvp.net.RetrofitManager
import com.x_meteor.kotlindemo.rx.scheduler.SchedulerUtils
import com.x_meteor.meteor.report.contract.ReportDetailContract
import com.x_meteor.meteor.report.model.bean.ZhihuDetailNote
import io.reactivex.Observable

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/15 16:58
 * @company:
 * @email: lx802315@163.com
 */
class ReportDetailModel : ReportDetailContract.ReportDetailModel {

    override fun getReportDetail(reportId: String): Observable<ZhihuDetailNote> {
        RetrofitManager.baseUrl = UrlConstant.ZHIHU_BASE_URL
        return RetrofitManager.service.getDetailZhhuNote(reportId)
            .compose(SchedulerUtils.ioToMain())
    }

}