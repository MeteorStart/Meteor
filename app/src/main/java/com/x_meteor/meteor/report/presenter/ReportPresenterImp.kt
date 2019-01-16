package com.x_meteor.meteor.report.presenter

import com.x_meteor.kotlindemo.utils.LogUtils
import com.x_meteor.meteor.base.BasePresenter
import com.x_meteor.meteor.report.contract.ReportContract
import com.x_meteor.meteor.report.model.ReportModel

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/14 15:24
 * @company:
 * @email: lx802315@163.com
 */
class ReportPresenterImp : BasePresenter<ReportContract.View>(), ReportContract.Presenter {

    private val reportModel by lazy {
        ReportModel()
    }

    override fun getReportListForNet(date: String) {
        // 检测是否绑定 View
        checkViewAttached()
        //加载loading框
        mRootView?.showLoading()

        val disposable = reportModel.getReportListForNet(date)
            .subscribe({ items ->
                //请求成功回调
                LogUtils.print("请求成功")
                mRootView?.dismissLoading()
                mRootView?.setReportList(items)
            }, { error ->
                //请求成功回调
                LogUtils.print("请求失败")
            })

        addSubscription(disposable)
    }

    override fun getReportListForLocal() {
    }

    override fun saveReportToLocal() {
    }
}