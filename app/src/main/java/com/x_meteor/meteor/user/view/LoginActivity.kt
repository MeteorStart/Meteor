package com.x_meteor.meteor.user.view

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.transition.Explode
import android.view.View
import com.x_meteor.kotlindemo.base.BaseActivity
import com.x_meteor.meteor.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_login

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
        bt_go.setOnClickListener {

            val explode = Explode()
            explode.duration = 500
            window.exitTransition = explode
            window.reenterTransition = explode

            val oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent, oc2.toBundle())


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
    }

    override fun getNetData() {

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
