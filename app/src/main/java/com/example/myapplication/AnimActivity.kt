package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView


/**
 * Created by ckh on 2018/2/7.
 */

class AnimActivity : AppCompatActivity() {
    private var mImg1: ImageView? = null
    private var mImg2: ImageView? = null
    private var mImg3: ImageView? = null
    private var mImg4: ImageView? = null
    private var mImg5: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)
        mImg1 = findViewById(R.id.iv_img)
        mImg2 = findViewById(R.id.iv_img2)
        mImg3 = findViewById(R.id.iv_img3)
        mImg4 = findViewById(R.id.iv_img4)
        mImg5 = findViewById(R.id.iv_img5)
    }

    fun animStart(v: View) {
        floatAnim(mImg1, 0)
        floatAnim(mImg2, 800)
        floatAnim(mImg3, 1600)
        floatAnim(mImg4, 2400)
        floatAnim(mImg5, 3200)

    }

    //从小到大 再大到小
    private fun floatAnim(view: View?, delay: Long) {
        val translationX = ObjectAnimator().ofFloat(view, "translationX", 150, 0, -200, -200, 0, 0)
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f, 0f, 0, 0, 0)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f, 0f, 0, 0, 0)
        translationX.setRepeatCount(Animation.INFINITE)
        scaleX.setRepeatCount(Animation.INFINITE)
        scaleY.setRepeatCount(Animation.INFINITE)

        val animatorSet = AnimatorSet()  //组合动画
        animatorSet.playTogether(translationX, scaleX, scaleY) //设置动画
        animatorSet.duration = 3500//动画播放的时长
        animatorSet.startDelay = delay//延迟一点时间再开始
        translationX.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                view!!.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {

            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        animatorSet.start()

    }
}

