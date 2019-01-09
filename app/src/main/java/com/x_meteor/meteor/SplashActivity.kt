package com.x_meteor.meteor

import android.content.Intent
import com.x_meteor.kotlindemo.base.BaseActivity
import com.x_meteor.kotlindemo.utils.LogUtils
import com.x_meteor.meteor.user.view.HomeActivity
import com.x_meteor.meteor.widget.SplashView

class SplashActivity : BaseActivity() {
    override fun layoutId() = R.layout.activity_splash

    override fun initView() {
        SplashView.showSplashView(
            this,
            5,
            R.drawable.ic_launcher_background,
            object : SplashView.OnSplashViewActionListener {
                override fun onSplashImageClick(actionUrl: String?) {
                    LogUtils.print(msg = "点击广告$actionUrl")
                }

                override fun onSplashViewDismiss(initiativeDismiss: Boolean) {
                    LogUtils.print("时间到")
                    val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            })

        SplashView.updateSplashData(this, "http://img2.3lian.com/2014/f4/100/d/50.jpg", "www.baidu.com")
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun getNetData() {
    }

}
