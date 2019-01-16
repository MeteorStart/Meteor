package com.x_meteor.meteor.report.view.adapter

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.hazz.kotlinmvp.glide.GlideApp
import com.x_meteor.meteor.R
import com.x_meteor.meteor.base.MyViewHolder
import com.x_meteor.meteor.report.model.bean.ZhihuNote
import kotlinx.android.synthetic.main.item_report.view.*

/**
 * @author: X_Meteor
 * @description: 类描述
 * @version: V_1.0.0
 * @date: 2019/1/14 16:44
 * @company:
 * @email: lx802315@163.com
 */
class ReportAdapter(layoutResId: Int, data: MutableList<ZhihuNote.StoriesBean>?) :
    BaseQuickAdapter<ZhihuNote.StoriesBean, MyViewHolder>(layoutResId, data) {

    override fun convert(helper: MyViewHolder?, item: ZhihuNote.StoriesBean?) {

        helper?.setImagePath(R.id.ivImage, object : MyViewHolder.HolderImageLoader(item?.images?.get(0).toString()) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                    .load(path)
                    .placeholder(R.color.color_darker_gray)
                    .transition(DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .into(iv)
            }
        })

        helper?.setText(R.id.tvTitle, item?.title)


    }

}