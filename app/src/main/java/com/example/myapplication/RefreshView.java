package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 *
 * Created by 马振雷 on 2018/2/7.
 */

public class RefreshView extends View implements RefreshHeader {


     private Paint mPaint;

    public RefreshView(Context context) {
        super(context);
        initView();
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView( );
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //初始化
        mPaint=new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas = new Canvas();
        canvas.drawColor(Color.parseColor("#666666"));
        //画笔的风格，就是边框（绘制的是空心的）
        //paint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeWidth(5);
        Path path = new Path();
        //从哪个点开始绘制
        path.moveTo(0,400/2);
        //然后绘制到哪个点
        path.lineTo(400/2,400);
        //然后再绘制到哪个点
        path.lineTo(400,400/2);
        //然后再绘制到哪个点
        path.lineTo(400/2,0);
        //然后再绘制到哪个点
        path.lineTo(0,400/2);
        //按路径绘制，就是一个菱形
        canvas.drawPath(path,mPaint);
    }

    //下拉事件
    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    //释放
    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @NonNull
    @Override
    public View getView() {
        return null;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return null;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public void onFinish(RefreshLayout layout) {

    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }
}
