package com.x_meteor.meteor.user.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.hazz.kotlinmvp.api.UrlConstant
import com.hazz.kotlinmvp.net.RetrofitManager
import com.x_meteor.kotlindemo.base.BaseActivity
import com.x_meteor.meteor.R
import com.x_meteor.meteor.base.BaseFragmentAdapter
import com.x_meteor.meteor.report.view.frag.ReportFrag
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList

class HomeActivity : BaseActivity() {

    private var pagerAdapter: FragmentStatePagerAdapter? = null
    //声明一个集合用于存放Fragment
    private var fragments: ArrayList<Fragment>? = null

    override fun layoutId() = R.layout.activity_home

    override fun initView() {
        bottomMain.enableAnimation(false)
        bottomMain.enableShiftingMode(false)
        vpHome.offscreenPageLimit = 4
    }

    override fun initData() {
        //初始化数据
        fragments = ArrayList()
        fragments?.add(ReportFrag())
        fragments?.add(ReportFrag())
        fragments?.add(ReportFrag())
        fragments?.add(ReportFrag())
        fragments?.add(ReportFrag())

        pagerAdapter = BaseFragmentAdapter(supportFragmentManager, fragments!!)
        //为viewpager设置适配器
        vpHome?.adapter = pagerAdapter

        bottomMain?.setupWithViewPager(vpHome)

    }

    override fun initListener() {
        //viewpager页面切换换监听
        vpHome.addOnAdapterChangeListener(object : ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
            override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottomMain.menu.getItem(position).isChecked = true
            }

        })

        //设置viewPager为不可滑动状态,存在主Fragment无法切换左右切换
        vpHome?.setOnTouchListener { v, event ->
            //修改为true 不可滑动，false 可滑动
            false
        }

        //bottom点击监听
        bottomMain.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.btn_report -> vpHome.currentItem = 0
                R.id.btn_movie -> vpHome.currentItem = 1
                R.id.btn_developer -> vpHome.currentItem = 2
                R.id.btn_welfare -> vpHome.currentItem = 3
                R.id.btn_my -> vpHome.currentItem = 4
                else -> -1
            }
        }

    }

    override fun getNetData() {
    }
}
