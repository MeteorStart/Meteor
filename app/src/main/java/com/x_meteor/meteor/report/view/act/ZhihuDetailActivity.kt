package com.x_meteor.meteor.report.view.act

import android.os.Bundle
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.glide.GlideApp
import com.x_meteor.kotlindemo.base.BaseActivity
import com.x_meteor.kotlindemo.utils.LogUtils
import com.x_meteor.meteor.R
import com.x_meteor.meteor.report.contract.ReportDetailContract
import com.x_meteor.meteor.report.model.bean.ZhihuDetailNote
import com.x_meteor.meteor.report.presenter.ReportDetailPresenterImp
import com.x_meteor.meteor.utils.HtmlUtil
import kotlinx.android.synthetic.main.activity_zhihu_detail.*

class ZhihuDetailActivity : BaseActivity(), ReportDetailContract.View {

    private var reportId = ""
    private var title = ""
    private var imageUrl = ""

    private val reportDetailPresenterImp by lazy {
        ReportDetailPresenterImp()
    }

    override fun layoutId() = R.layout.activity_zhihu_detail

    override fun initView() {
        reportDetailPresenterImp.attachView(this)
    }

    override fun initData() {
        reportId = intent.getStringExtra("reportId")
        title = intent.getStringExtra("title")
        imageUrl = intent.getStringExtra("imageUrl")

        reportDetailPresenterImp.getReportDetail(reportId)
    }

    override fun initListener() {
    }

    override fun getNetData() {
    }

    override fun setReportDetail(detail: ZhihuDetailNote) {
        LogUtils.print(detail?.title)
        zhihu_bar_title.text = detail.title
        zhihu_bar_copyright.text = detail.image_source

        GlideApp.with(this)
            .load(detail.image)
            .placeholder(R.color.color_darker_gray)
            .transition(DrawableTransitionOptions().crossFade())
            .thumbnail(0.5f)
            .into(zhihu_bar_image)

        val htmldata = HtmlUtil.createHtmlData(
            detail.body,
            detail.css,
            detail.js,
            false
        )
        zhihu_detail_webview.loadData(htmldata, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING)
        toolbar_title.text = detail.title
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

}
