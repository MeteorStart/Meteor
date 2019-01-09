package com.x_meteor.meteor.user.view

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.text.Editable
import android.text.TextWatcher
import android.transition.Explode
import android.view.View
import com.x_meteor.kotlindemo.base.BaseActivity
import com.x_meteor.meteor.R
import com.x_meteor.meteor.user.contract.UserContract
import com.x_meteor.meteor.user.presenter.UserPresenterImp
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), UserContract.UserView {

    private lateinit var userName: String

    private lateinit var paw: String

    private var etUser = false

    private var etPaw = false

    override fun jumpToMain() {
        val explode = Explode()
        explode.duration = 500
        window.exitTransition = explode
        window.reenterTransition = explode

        val oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val intent = Intent(this, HomeActivity::class.java)

        clearActicity()
        startActivity(intent, oc2.toBundle())
    }

    override fun showError() {
    }

    override fun showLoading() {
        showProcessDialog("登录中。。。", "", false)
    }

    override fun dismissLoading() {
        dismissProcessDialog()
    }

    private val mPresenter by lazy {
        UserPresenterImp()
    }

    override fun layoutId() = R.layout.activity_login

    override fun initView() {
        mPresenter.attachView(this)
    }

    override fun initData() {
    }

    override fun initListener() {

        bt_go.setOnClickListener {
            mPresenter.login(userName, paw)
        }

        fab.setOnClickListener {
            window.exitTransition = null
            window.enterTransition = null
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                fab, fab.transitionName
            )
            startActivity(
                Intent(this, RegisterActivity::class.java),
                optionsCompat.toBundle()
            )
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
    }

    override fun getNetData() {

    }

    fun setBtnEnable(etUser: Boolean, etPaw: Boolean) {
        bt_go.isEnabled = etUser && etPaw
    }

    override fun onRestart() {
        super.onRestart()
        fab.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        fab.visibility = View.VISIBLE
    }
}
