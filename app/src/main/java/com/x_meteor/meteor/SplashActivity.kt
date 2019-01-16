package com.x_meteor.meteor

import android.content.Intent
import com.x_meteor.kotlindemo.base.BaseActivity
import com.x_meteor.kotlindemo.utils.LogUtils
import com.x_meteor.meteor.user.view.LoginActivity
import com.x_meteor.meteor.widget.SplashView
import cn.bmob.v3.BmobUser
import com.x_meteor.meteor.user.model.bean.UserBean
import com.x_meteor.meteor.user.view.HomeActivity


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
                    val bmobUser = BmobUser.getCurrentUser(UserBean::class.java)
                    //需要添加登录情况验证
                    val intent = if (bmobUser != null) {
                        //跳转到首页
                        Intent(this@SplashActivity, HomeActivity::class.java)
                    } else {
                        //跳转到登录页
                        Intent(this@SplashActivity, LoginActivity::class.java)
                    }

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
