package com.x_meteor.meteor.user.model.bean

import cn.bmob.v3.BmobObject
import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobFile


/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/3 14:44
 * @company:
 * @email: lx802315@163.com
 */
class UserBean : BmobUser() {

    /**
     * 昵称
     */
    private val nickname: String? = null

    /**
     * 年龄
     */
    private val age: Int? = null

    /**
     * 性别
     */
    private val gender: Int? = null

    /**
     * 头像
     */
    private val avatar: BmobFile? = null

    override fun toString(): String {
        return "UserBean(username=$username, nickname=$nickname, age=$age, gender=$gender, avatar=$avatar)"
    }
}