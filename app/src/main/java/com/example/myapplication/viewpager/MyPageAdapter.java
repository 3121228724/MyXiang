package com.example.myapplication.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 轮播图的适配器设置
 * Created by Administrator on 2016/12/5.
 */

public class MyPageAdapter extends PagerAdapter {

    private List<View> listViews = null;

    public MyPageAdapter(List<View> listViews) {
        this.listViews = listViews;
    }

    @Override
    public int getCount() {
        return listViews.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    /**
     *每当Adapter调用instantiateItem时，运用View.setTag方法将该View标识。
     *当需要更新这个View的数据时，通过调用ViewPager.findViewWithTag方法找到相应的View，然后更新View中的数据。
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(listViews.get(position));

        return listViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
       // container.removeView(listViews.get(position));
    }
}
