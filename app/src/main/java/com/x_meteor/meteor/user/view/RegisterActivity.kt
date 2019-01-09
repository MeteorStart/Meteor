package com.x_meteor.meteor.user.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import com.x_meteor.kotlindemo.base.BaseActivity
import com.x_meteor.kotlindemo.utils.ToastUtils
import com.x_meteor.meteor.R
import com.x_meteor.meteor.user.contract.UserContract
import com.x_meteor.meteor.user.presenter.UserPresenterImp
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), UserContract.UserView {

    private lateinit var userName: String

    private lateinit var paw: String

    private var etUser = false

    private var etPaw = false

    private val mPresenter by lazy {
        UserPresenterImp()
    }

    override fun layoutId() = R.layout.activity_register

    override fun initView() {
        mPresenter.attachView(this)
        showEnterAnimation()
    }

    override fun initData() {
    }

    override fun initListener() {
        fab.setOnClickListener {
            animateRevealClose()
        }

        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                etUser = s?.length != 0
                userName = s.toString()
                setBtnEnable(etUser, etPaw)
            }
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                etPaw = s?.length != 0
                paw = s.toString()
                setBtnEnable(etUser, etPaw)
            }
        })


        btn_register.setOnClickListener {
            mPresenter.register(userName, paw)
        }
    }

    override fun getNetData() {
    }

    override fun jumpToMain() {
        ToastUtils.showToast("开始跳转")
        val intent = Intent(this, HomeActivity::class.java)
        clearActicity()
        startActivity(intent)
    }

    override fun showError() {
    }

    override fun showLoading() {
        showProcessDialog("注册中。。。", "", false)
    }

    override fun dismissLoading() {
        dismissProcessDialog()
    }

    fun setBtnEnable(etUser: Boolean, etPaw: Boolean) {
        btn_register.isEnabled = etUser && etPaw
    }

    /**
     * @description: 显示进入动画
     * @date: 2018/12/29 16:59
     * @author: Meteor
     * @email: lx802315@163.com
     */
    private fun showEnterAnimation() {
        val transient = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition)
        window.sharedElementEnterTransition = transient

        transient.addListener(object : Transition.TransitionListener {

            override fun onTransitionStart(transition: Transition?) {
                cv_add.visibility = View.GONE
                animateRevealShow()
            }

            override fun onTransitionEnd(transition: Transition?) {
                transition?.removeListener(this)
            }

            override fun onTransitionResume(transition: Transition?) {

            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

        })
    }

    /**
     * @description: 动画显示
     * @date: 2018/12/29 17:18
     * @author: Meteor
     * @email: lx802315@163.com
     */
    private fun animateRevealShow() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(
            cv_add, cv_add.width / 2, 0,
            (fab.width / 2).toFloat(), cv_add.height.toFloat()
        )
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator) {
                cv_add.visibility = View.VISIBLE
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }

    /**
     * @description: 动画关闭
     * @date: 2018/12/29 17:18
     * @author: Meteor
     * @email: lx802315@163.com
     */
    private fun animateRevealClose() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(
            cv_add,
            cv_add.width / 2,
            0,
            cv_add.height.toFloat(),
            (fab.width / 2).toFloat()
        )
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                cv_add.visibility = View.INVISIBLE
                super.onAnimationEnd(animation)
                fab.setImageResource(R.drawable.plus)
                super@RegisterActivity.onBackPressed()
            }
        })
        mAnimator.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        animateRevealClose()
    }
}


