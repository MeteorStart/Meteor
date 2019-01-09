package com.x_meteor.meteor.base

import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.Log
import cn.bmob.v3.Bmob
import com.orhanobut.logger.*
import com.squareup.leakcanary.RefWatcher
import com.x_meteor.kotlindemo.utils.DisplayManager
import com.x_meteor.kotlindemo.utils.LogUtils
import java.util.*
import kotlin.properties.Delegates

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2018/10/26 13:59
 * @company:
 * @email: lx802315@163.com
 */
class MyApplication : Application() {

    private var refWatcher: RefWatcher? = null

    /**
     * 声明一个活动管理栈
     */
    private val activities = Stack<Activity>()

    companion object {
        private val TAG = "MyApplication"

        var context: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }

        fun getActivities():Stack<Activity>{
            val myApplication = context.applicationContext as MyApplication
            return myApplication.activities
        }

        /**
         * @name: 判断当前应用是否是debug状态
         * @description: 方法描述
         * @date: 2018/9/17 15:09
         * @company:
         * @author: Meteor
         */
        fun isApkInDebug(context: Context): Boolean {
            try {
                var info = context.applicationInfo as ApplicationInfo
                return (info.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
            } catch (e: Exception) {
                return false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initLogger()
        initBmob()
        DisplayManager.init(this)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    /**
     * @description: 初始化log打印
     * @date: 2018/10/10 16:20
     * @author: Meteor
     * @email: lx802315@163.com
     */
    private fun initLogger() {
        if (isApkInDebug(this)) {
            val preFormaatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(1)
                .methodOffset(1)
                .tag("X_Meteor")
                .build()
            Logger.addLogAdapter(AndroidLogAdapter(preFormaatStrategy))
            LogUtils.isDebug = true
        } else {
            val csvFormatStrategy = CsvFormatStrategy.newBuilder()
                .tag("X_Meteor")
                .build()
            Logger.addLogAdapter(DiskLogAdapter(csvFormatStrategy))
            LogUtils.isDebug = false
        }
    }

    private fun initBmob() {
        //第一：默认初始化
        Bmob.initialize(this, Constants.Bmob_APP_KEY)
    }

    /**
     * @name: 重启当前应用
     * @description: 方法描述
     * @date: 2018/9/17 14:59
     * @company:
     * @author: Meteor
     */
    fun restartApplication() {
        val mStartActivity = packageManager.getLaunchIntentForPackage(packageName)
        val mPendingIntentId = 1234567
        val mPendingIntent =
            PendingIntent.getActivity(this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT)
        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
        System.exit(0)
    }

    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }
}

