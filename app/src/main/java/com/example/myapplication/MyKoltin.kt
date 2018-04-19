package com.example.myapplication

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


/**
 * ：代表继承
 * Created by 马振雷 on 2018/2/24.
 */
class MyKoltin : Activity() {
    //定义变量常量 结尾不用符号
    lateinit var img: ImageView   //定义了一个imageView  相当于private ImageView img; :代表集成

    lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)
        img = findViewById(R.id.iv_img2)

    }

    //fun用来定义方法
    fun FangFa(v: View) {

        recycleView.layoutManager = LinearLayoutManager(this)//实例化
    }

    fun set(zer: String) {
    }


    class Gree(a: String) {
        fun gree() {

        }
    }

    //设置阴影
    private fun setBack(dimView : ViewGroup){
        val color=ColorDrawable(Color.BLACK)
        color.setBounds(0,0,dimView.width,dimView.height)
        color.alpha=(225.0f*0.7).toInt()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            dimView.overlay.add(color)

            dimView.overlay.clear()//清理阴影
        }
    }
}