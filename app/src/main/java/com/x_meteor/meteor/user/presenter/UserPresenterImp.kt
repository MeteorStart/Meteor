package com.x_meteor.meteor.user.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.SaveListener
import com.x_meteor.kotlindemo.utils.LogUtils
import com.x_meteor.kotlindemo.utils.ToastUtils
import com.x_meteor.meteor.base.BasePresenter
import com.x_meteor.meteor.base.MyApplication
import com.x_meteor.meteor.user.contract.UserContract
import com.x_meteor.meteor.user.model.UserModel
import com.x_meteor.meteor.user.model.bean.UserBean

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/3 15:15
 * @company:
 * @email: lx802315@163.com
 */
class UserPresenterImp : BasePresenter<UserContract.UserView>(), UserContract.UserPresenter {

    private val userModel: UserModel by lazy {
        UserModel()
    }

    override fun register(userName: String, paw: String) {
        //检查是否绑定View
        checkViewAttached()
        //显示请求框
        mRootView?.showLoading()
        var userBean = UserBean()

        userBean.username = userName
        userBean.setPassword(paw)

        userModel.register(userBean, object : SaveListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 == null) {
                    LogUtils.print(userBean.toString())
                    //注册成功
                    mRootView?.apply {
                        LogUtils.print("注册成功")
                        dismissLoading()
                        if (userBean != null) {
                            loginByBean(userBean)
                        }
                    }
                } else {
                    ToastUtils.showCenterToast(MyApplication.context, p1.message + "")
                }
            }
        })

    }

    override fun login(userName: String, paw: String) {
        BmobUser.loginByAccount(userName, paw, object : LogInListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 == null) {
                    val userBean = BmobUser.getCurrentUser(UserBean::class.java)
                    p0?.setPassword(paw)
                    LogUtils.print(userBean.toString())
                    //注册成功
                    mRootView?.apply {
                        dismissLoading()
                        jumpToMain()
                    }
                } else {
                    ToastUtils.showCenterToast(MyApplication.context, p1.message + "")
                }
            }

        })
    }

    fun loginByBean(userBean: UserBean) {
        userBean.login(object : SaveListener<UserBean>() {
            override fun done(p0: UserBean?, p1: BmobException?) {
                if (p1 == null) {
                    val userBean = BmobUser.getCurrentUser(UserBean::class.java)
                    LogUtils.print(userBean.toString())
                    //登录
                    mRootView?.apply {
                        dismissLoading()
                        jumpToMain()
                    }
                } else {
                    ToastUtils.showCenterToast(MyApplication.context, p1.message + "")
                }
            }

        })
    }
}