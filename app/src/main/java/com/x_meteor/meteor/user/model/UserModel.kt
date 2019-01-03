package com.x_meteor.meteor.user.model

import cn.bmob.v3.BmobUser
import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.SaveListener
import com.x_meteor.meteor.user.model.bean.UserBean

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/3 14:53
 * @company:
 * @email: lx802315@163.com
 */
class UserModel {

    fun register(userBean: UserBean, saveListener: SaveListener<UserBean>) {
        userBean.signUp(saveListener)
    }

    fun login(userName: String, paw: String, loginListener: LogInListener<UserBean>) {
        BmobUser.loginByAccount(userName, paw, loginListener)
    }
}