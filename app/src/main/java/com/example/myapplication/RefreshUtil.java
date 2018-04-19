package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

/**
 * 刷新头部
 * Created by 马振雷 on 2018/2/7.
 */

public class RefreshUtil {

    //从大到小
     static void floatAnim1(View view){
        //自己位置x坐标向左移动80
        ObjectAnimator translationX = new ObjectAnimator().ofFloat(view,"translationX",0,-150);
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(view,"translationY",0,0);

        //缩放
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f);

        translationX.setRepeatCount(Animation.INFINITE);
        translationY.setRepeatCount(Animation.INFINITE);
        scaleX.setRepeatCount(Animation.INFINITE);
        scaleY.setRepeatCount(Animation.INFINITE);


        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(translationX,translationY,scaleX,scaleY); //设置动画
        animatorSet.setDuration(1000);  //设置动画时间
        animatorSet.start();
    }


    //从小到大
     static void floatAnim2(View view){
        ObjectAnimator translationX = new ObjectAnimator().ofFloat(view,"translationX",150,0);
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(view,"translationY",0,0);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f);

        translationX.setRepeatCount(Animation.INFINITE);
        translationY.setRepeatCount(Animation.INFINITE);
        scaleX.setRepeatCount(Animation.INFINITE);
        scaleY.setRepeatCount(Animation.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(translationX,translationY,scaleX,scaleY); //设置动画
        animatorSet.setDuration(1000);  //设置动画时间
        animatorSet.start();
    }

    //从小到大
    static void floatAnim3(View view){
        ObjectAnimator translationX = new ObjectAnimator().ofFloat(view,"translationX",150,0,-75,-150,0,150);
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(view,"translationY",0,0);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f);

        translationX.setRepeatCount(Animation.INFINITE);
        translationY.setRepeatCount(Animation.INFINITE);
        scaleX.setRepeatCount(Animation.INFINITE);
        scaleY.setRepeatCount(Animation.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(translationX,translationY,scaleX,scaleY); //设置动画
        animatorSet.setDuration(1000);  //设置动画时间
        animatorSet.setStartDelay(500);
        animatorSet.start();
    }

    //从小到大 再大到小
    static void floatAnim(View view, long delay) {
        ObjectAnimator translationX = new ObjectAnimator().ofFloat
                (view, "translationX", 150, 0, -150, -150, 0, 150);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f, 0f, 0, 0, 0);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f, 0f, 0, 0, 0);

        translationX.setRepeatCount(Animation.INFINITE);
        scaleX.setRepeatCount(Animation.INFINITE);
        scaleY.setRepeatCount(Animation.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();  //组合动画 //组合动画
        animatorSet.playTogether(translationX, scaleX, scaleY);//设置动画
        animatorSet.setDuration(2000) ;//动画播放的时长
        animatorSet.setStartDelay(500);//延迟一点时间再开始
        animatorSet.start();

    }


     static void floatAnimAll(final View view, int n) {
        ObjectAnimator translationX =  ObjectAnimator.ofFloat(view, "translationX", 120, 90,60,30, 0, -30, -60, -90, -120);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.1f,0.2f,0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.1f,0.2f,0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f);
        translationX.setRepeatCount(Animation.INFINITE);
        scaleY.setRepeatCount(Animation.INFINITE);
        scaleX.setRepeatCount(Animation.INFINITE);
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        animatorSet.playTogether(translationX, scaleX, scaleY); //设置动画
        //控制速度，要结合发射imageview的时间改动
        animatorSet.setDuration(1800);
        animatorSet.setStartDelay(n);
//         animatorSet.addListener(new Animator.AnimatorListener() {
//             @Override
//             public void onAnimationStart(Animator animator) {
//                 view.setVisibility(View.VISIBLE);
//             }
//
//             @Override
//             public void onAnimationEnd(Animator animator) {
//
//             }
//
//             @Override
//             public void onAnimationCancel(Animator animator) {
//
//             }
//
//             @Override
//             public void onAnimationRepeat(Animator animator) {
//             }
//         });
         animatorSet.addListener(new AnimatorListenerAdapter() {
             @Override
             public void onAnimationCancel(Animator animation) {
                 super.onAnimationCancel(animation);
             }

             @Override
             public void onAnimationRepeat(Animator animation) {
                 super.onAnimationRepeat(animation);
             }

             @Override
             public void onAnimationStart(Animator animation) {
                 super.onAnimationStart(animation);
                 view.setVisibility(View.VISIBLE);
             }
         });
        animatorSet.start();



    }




}
