package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 *
 * @author shiqi
 * @date 2018/2/7
 */

public class DiamondView extends View {

    /**
     * 菱形画笔
     */
    private Paint mPaint;
    /**
     * Path画线的路径和填充色
     */
    private Path mPath;
    /**
     * 菱形运动范围的正方形的宽高
     */
    private int viewSize;
    /**
     * 默认初始的宽度
     */
    private int defaultWidth;
    /**
     * 菱形运动范围的正方形是菱形的倍数
     */
    private int mSize;
    /**
     * 菱形的半径
     */
    private int diamondViewSize = 0;
    /**
     * 平移的动画
     */
    private DiamondAnimation mAnimation;
    /**
     * 当前动画的时间进度
     */
    private float percent;

    public DiamondView(Context context) {
        this(context,null);
    }

    public DiamondView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiamondView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
        initAnimation();
    }

    private void initPaint(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPath = new Path();
        defaultWidth = DpOrPxUtils.dip2px(context,80);
    }

    private void initAnimation() {
        mAnimation = new DiamondAnimation();
    }

    public class DiamondAnimation extends Animation {

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            //interpolatedTime表示当前进度（0-1）
            percent = interpolatedTime;
            postInvalidate();
        }
    }

    /**
     * 设置进度条数值
     * @param time 动画持续时间
     */
    public void setProgressNum(int time) {
        mAnimation.setDuration(time);
        mAnimation.setRepeatCount(Animation.INFINITE);
        //让动画无限循环
        mAnimation.setInterpolator(new LinearInterpolator());
        //让动画匀速播放，不然会出现平移停顿的现象
        this.startAnimation(mAnimation);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将宽高强制设置为一样的
        int height = measureSize(defaultWidth, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);
        int min = Math.min(width, height);
        // 获取View最短边的长度
        setMeasuredDimension(min, min);
        // 强制改View为以最短边为长度的正方形
        viewSize = min;
        mSize = 4;
        diamondViewSize = viewSize/(2*mSize);
    }

    private int measureSize(int defaultSize,int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //最左边的点开始  坐标
        mPath.moveTo(viewSize/2 -(viewSize/2)*(1-percent)-diamondViewSize*percent,viewSize/ 2);
        //移动到上边
        mPath.lineTo((viewSize/2)*percent,viewSize/2 -diamondViewSize*percent);
        //移动到右边
        mPath.lineTo(viewSize/2 -(viewSize/2)*(1-percent)+diamondViewSize*percent,viewSize/2);
        //移动到下面
        mPath.lineTo((viewSize/2)*percent,viewSize/2 +diamondViewSize*percent);
        canvas.drawPath(mPath,mPaint);

        //移动到左边
        mPath.moveTo(viewSize/2+(viewSize/2)*percent-diamondViewSize*(1-percent),viewSize/ 2);
        //移动到上边
        mPath.lineTo(viewSize/2+(viewSize/2)*percent,viewSize/2 -diamondViewSize*(1-percent));
        //移动到右边
        mPath.lineTo(viewSize/2+(viewSize/2)*percent+diamondViewSize*(1-percent),viewSize/2);
        //移动到下面
        mPath.lineTo(viewSize/2+(viewSize/2)*percent,viewSize/2 +diamondViewSize*(1-percent));
        canvas.drawPath(mPath,mPaint);
    }
}
