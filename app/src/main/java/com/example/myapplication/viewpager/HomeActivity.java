package com.example.myapplication.viewpager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *这里的recycleView是WrapRecyclerView
 * viewPager两种实现方式 现在用的是循环播放 用了第三种    还没给viewpager设置监听事件
 * Created by Administrator on 2016/12/5.
 */

public class HomeActivity extends Activity {

    MyPageAdapter myPageAdapter;
    ViewPager viewPager;
    List<View>  listViews = new ArrayList<>();
    List viewPagerList =new ArrayList<>();

    Timer mTimer;
    TimerTask mTask;
    int pageIndex = 0;
    boolean isTaskRun;
    int i;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);


//        viewPager=findViewById(R.id.view_pager);
//        setViewPager();
//        myPageAdapter=new MyPageAdapter(listViews);
//        viewPager.setAdapter(myPageAdapter);
//        viewPager.setCurrentItem(0);
//        myPageAdapter.notifyDataSetChanged();

        setNameEdit();//.setRepeatMode(Animation.RESTART)

        //无限循环播放
        Animation animation= AnimationUtils.loadAnimation(this, R.anim.scale);
        animation.setRepeatCount(Animation.INFINITE);
        findViewById(R.id.animation).setAnimation(
                animation);
        animation.start();


        //string.matches("^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$")

    }

    //跳转到翼支付
    public void goToYizhifu(View view){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        //前提：知道要跳转应用的包名、类名
        ComponentName componentName = new ComponentName("com.chinatelecom.bestpayclient", "com.chinatelecom.bestpayclient.PaymentActivity");
        intent.setComponent(componentName);
        startActivity(intent);

    }
    void setNameEdit(){
        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String name=source.toString();
                //[^\u4e00-\u9fa5·]
                if(specialStrFilter(name))//只允许中文 代表不是中文)
                     {
                    if (name.equals("·")){
                        //可以输入点
                        return null;
                    }else {
                        return "";
                    }
                } else{
                    return null;
                }
//                    if (name.equals(".")){
//                      return null;
//                    }
            }
        };
        EditText editText=findViewById(R.id.edit);
        editText.setFilters(new InputFilter[]{filter});
    }
    public  boolean specialStrFilter(String name)
            throws PatternSyntaxException {
        String regEx = "[^\u4E00-\u9FA5]";
        // 不允许输入中英文特殊字符、数字和数学符号
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        return m.find();
    }


//    void setNameEdit(){
//        InputFilter filter=new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                String name=source.toString();
//                if(name.equals(" ")
//                        ||isSpecialChar(name)
//                        ||name.matches("[0-9]+")
//                        ||name.trim().contains(" ")
//                        ||name.matches("[^a-zA-Z]")
//                        ||name.matches("^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$")
//                        )
//                    return "";
//                else
//                    return null;
//            }
//        };

//        EditText editText=findViewById(R.id.edit);
//        editText.setFilters(new InputFilter[]{filter});
//    }


    public  boolean isSpecialChar(String str) {
        //"[A-Za-z0-9]+.[A-Za-z0-9]*[\\/=?%-&_~`@\\[\\]':+!]*([^<>\"\"])*$"
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    //请求得到轮播图数据
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setViewPager() {
        //给集合加数据
        for (i=0;i<3;i++){
            @SuppressLint("InflateParams")
            View view = LayoutInflater.from(this).inflate(
                    R.layout.viewpager_item, null);
            ImageView imageView = view.findViewById(R.id.image);
            if (i==0){
                imageView.setBackground(getResources().getDrawable(R.drawable.share_exclusive));
            }else if (i==1){
                imageView.setBackground(getResources().getDrawable(R.drawable.trans_amount));
            }else {
               imageView.setBackground(getResources().getDrawable(R.drawable.account_sm_idcard_behind));
            }

            listViews.add(view);
        }


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == 0 && !isTaskRun) {
                    setCurrentItem();
                    startTask();
                } else if (state == 1 && isTaskRun)
                    stopTask();
            }
        });
      //viewpager的点击事件
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            int flag;
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        flag = 0;
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        flag = 1;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        if (flag == 0) {
//                            //得到对应条目的值传递过去
//                            int item = viewPager.getCurrentItem();
//                            Intent intent = new Intent(getActivity(), DetailsActivity.class);
//                            intent.putExtra("id",viewPagerList.get(item).getId());
//                            intent.putExtra("flag","3");
//                            startActivity(intent);
//                        }
//                        break;
//                }
//                    return false;
//            }
//        });
    }




    /**
     * 开启定时任务
     */
    private void startTask() {
        // TODO Auto-generated method stub
        isTaskRun = true;
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                pageIndex++;
                mHandler.sendEmptyMessage(0);
            }
        };
        mTimer.schedule(mTask, 2 * 1000, 2 * 10100);// 这里设置自动切换的时间，单位是毫秒，2*1000表示2秒
    }

    // 处理EmptyMessage(0)
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setCurrentItem();
        }
    };

    /**
     * 处理Page的切换逻辑
     * pageIndex的大小根据viewpager数量决定 如果联网没有数量会导致空指针
     */
    private void setCurrentItem() {

        if (pageIndex == 0) {
            pageIndex = 2;
        } else if (pageIndex == viewPagerList.size()) {
            pageIndex = 0;
        }
        if (viewPager==null){
            Log.d("","");
        }else {
            viewPager.setCurrentItem(pageIndex, false);// 取消动画
        }

    }

    /**
     * 停止定时任务
     */
    private void stopTask() {
        // TODO Auto-generated method stub
        isTaskRun = false;
        mTimer.cancel();
    }

    public void onResume() {
        super.onResume();
        setCurrentItem();
        startTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopTask();
    }

}
