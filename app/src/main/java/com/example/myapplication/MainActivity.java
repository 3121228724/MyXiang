package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View{


    private MainContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView money=findViewById(R.id.money);

        NumAnim.startAnim(money, "0.01", 1000);

//        RefreshUtil.floatAnimAll(findViewById(R.id.iv1),0);
//        RefreshUtil.floatAnimAll(findViewById(R.id.iv2),600);
//        RefreshUtil.floatAnimAll(findViewById(R.id.iv3),1200);

        //从右到左

        // 通过逐帧动画的资源文件获得AnimationDrawable示例
//        AnimationDrawable   frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.anmial);
//        // 把AnimationDrawable设置为ImageView的背景
//        findViewById(R.id.iv1).setBackgroundDrawable(frameAnim);
//        final ImageView imageView=findViewById(R.id.iv1);
//        imageView.postDelayed(new Runnable() {     //采用延迟启动子线程的方式
//            public void run() {
//                AnimationDrawable   frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.anmial);
//                imageView.setBackgroundDrawable(frameAnim);frameAnim.start();
//
//            }
//        },10);

        //frameAnim.start();

        SurfaceView surfaceView=findViewById(R.id.iv1);
        SilkyAnimation mAnimation=
                new SilkyAnimation.Builder(surfaceView)
                        .setFrameInterval(80)
                        .setScaleType(SilkyAnimation.SCALE_TYPE_FIT_XY)
                        .build();
        mAnimation.start("baozha");




//
//        floatAnim1(findViewById(R.id.iv1),false);
//        floatAnim2(findViewById(R.id.iv2),false);
//
//
//
//        floatAnim1(findViewById(R.id.iv3),false);
//        floatAnim2(findViewById(R.id.iv4),false);


//        presenter=new MyPresenter();
//        presenter.attachView(this);//把view传递给p
//
//        RecyclerView recyclerView=findViewById(R.id.recycle_ad_list);
//        SmartRefreshLayout smartRefreshLayout=findViewById(R.id.refresh);
//        smartRefreshLayout.setEnableLoadmore(false);
//
//
//        List<String> list=new ArrayList<>();
//        for (int i = 0; i <20 ; i++) {
//            list.add("i");
//        }
//
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        MyAdapter myAdapter=new MyAdapter(list);
//        recyclerView.setAdapter(myAdapter);
    }


    @Override
    public void initViews() {

    }

    @Override
    public void setAdapter(MyAdapter adapter) {

    }

    @Override
    public void loadCompleted() {

    }
}
