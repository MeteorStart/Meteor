package com.x_meteor.meteor.user.contract

import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.SaveListener
import com.x_meteor.meteor.base.IBaseView
import com.x_meteor.meteor.base.IPresenter
import com.x_meteor.meteor.user.model.bean.UserBean

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/3 14:31
 * @company:
 * @email: lx802315@163.com
 */
class UserContract {
    interface UserView : IBaseView {

        fun jumpToMain()

        fun showError()
    }

    interface UserPresenter : IPresenter<UserView> {

        fun register(userName: String, paw: String)

        fun login(userName: String, paw: String)
    }
}