package com.x_meteor.meteor.report.presenter

import com.x_meteor.kotlindemo.utils.LogUtils
import com.x_meteor.meteor.base.BasePresenter
import com.x_meteor.meteor.report.contract.ReportDetailContract
import com.x_meteor.meteor.report.model.ReportDetailModel
import com.x_meteor.meteor.report.model.ReportModel

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/15 17:06
 * @company:
 * @email: lx802315@163.com
 */
class ReportDetailPresenterImp : BasePresenter<ReportDetailContract.View>(), ReportDetailContract.Presenter {

    private val reportDetailModel by lazy {
        ReportDetailModel()
    }

    override fun getReportDetail(reportId: String) {
        // 检测是否绑定 View
        checkViewAttached()
        //加载loading框
        mRootView?.showLoading()

        reportDetailModel.getReportDetail(reportId)
            .subscribe({ item ->
                mRootView?.setReportDetail(item)
            }, { error ->
                LogUtils.print("")
            })
    }

}