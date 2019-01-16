package com.x_meteor.meteor.report.view.frag

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Explode
import com.scwang.smartrefresh.header.MaterialHeader
import com.x_meteor.kotlindemo.utils.LogUtils
import com.x_meteor.kotlindemo.utils.StatusBarUtils
import com.x_meteor.meteor.R
import com.x_meteor.meteor.base.BaseFragment
import com.x_meteor.meteor.report.contract.ReportContract
import com.x_meteor.meteor.report.model.bean.ZhihuDetailNote
import com.x_meteor.meteor.report.model.bean.ZhihuNote
import com.x_meteor.meteor.report.presenter.ReportPresenterImp
import com.x_meteor.meteor.report.view.act.ZhihuDetailActivity
import com.x_meteor.meteor.report.view.adapter.ReportAdapter
import com.x_meteor.meteor.user.view.HomeActivity
import com.x_meteor.meteor.utils.AppDateMgr
import kotlinx.android.synthetic.main.frag_report.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/11 11:22
 * @company:
 * @email: lx802315@163.com
 */
class ReportFrag : BaseFragment(), ReportContract.View {

    private lateinit var reportAdapter: ReportAdapter

    private val reportList = ArrayList<ZhihuNote.StoriesBean>()

    private var date: String = ""

    private var loadingMore = false

    private var isRefresh = false

    private val dateMap: HashMap<Int, String> = HashMap()

    override fun setReportList(netData: ZhihuNote) {
        this.date = netData.date

        //格式化日期格式
        var s = AppDateMgr.parseDate(this.date, "yyyyMMdd")
        var dateString = AppDateMgr.formatDateDay(s, "yyyy-MM-dd")

        if (loadingMore) {
            loadingMore = false

            var oldSize = reportAdapter.data.size
            reportAdapter.addData(netData.stories)
            var newSize = reportAdapter.data.size
            saveTitleDate(oldSize, newSize - 1, dateString)

        } else {

            reportAdapter.setNewData(netData.stories)
            var dataSize = reportAdapter.data.size
            saveTitleDate(0, dataSize - 1, dateString)

        }
    }

    /**
     * @description: 存储标题
     * @date: 2019/1/15 11:43
     * @author: Meteor
     * @email: lx802315@163.com
     */
    private fun saveTitleDate(start: Int, end: Int, dateTitel: String) {
        for (index in start..end) {
            dateMap[index] = dateTitel
        }
    }

    override fun showError() {
    }

    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    override fun dismissLoading() {
        mLayoutStatusView?.showContent()
        mRefreshLayout.finishRefresh()
    }

    private val reportPresenterImp by lazy {
        ReportPresenterImp()
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun getLayoutId() = R.layout.frag_report

    override fun initView() {
        //状态栏透明和间距处理
        activity?.let { StatusBarUtils.darkMode(it) }
        activity?.let { StatusBarUtils.setPaddingSmart(it, toolBar) }

        reportPresenterImp.attachView(this)
        reportAdapter = ReportAdapter(R.layout.item_report, reportList)

        recyReport.adapter = reportAdapter
        recyReport.layoutManager = linearLayoutManager
        recyReport.itemAnimator = DefaultItemAnimator()

        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true)

        mRefreshLayout.setOnRefreshListener {
            isRefresh = true
            this.date = ""
            dateMap.clear()
            //请求数据
            reportPresenterImp.getReportListForNet(date)
        }

        recyReport.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = recyReport.childCount
                    val itemCount = recyReport.layoutManager.itemCount
                    val firstVisibleItem =
                        (recyReport.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    //下拉加载更多
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true
                            reportPresenterImp.getReportListForNet(date)
                        }
                    }
                }
            }

            //RecyclerView滚动的时候调用
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (currentVisibleItemPosition == 0) {
                    //背景设置为透明
                    toolBar.setBackgroundColor(getColor(R.color.color_translucent))
                    iv_search.setImageResource(R.drawable.ic_action_search_white)
                    tv_header_title.text = ""
                } else {
                    if (reportAdapter.data.size > 1) {
                        toolBar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setImageResource(R.drawable.ic_action_search_black)
                        if (dateMap.containsKey(currentVisibleItemPosition)) {
                            tv_header_title.text = dateMap[currentVisibleItemPosition]
                        }
                    }
                }
            }
        })

        reportAdapter.setOnItemClickListener { adapter, view, position ->
            var item = adapter.data[position] as ZhihuNote.StoriesBean
            LogUtils.print(item.title)

            val intent = Intent(activity, ZhihuDetailActivity::class.java)
            intent.putExtra("reportId", item.id.toString())
            intent.putExtra("title", item.title)
            intent.putExtra("imageUrl", item.images[0])
            startActivity(intent)
        }
    }

    override fun lazyLoad() {
        reportPresenterImp.getReportListForNet(date)
    }


    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }

}