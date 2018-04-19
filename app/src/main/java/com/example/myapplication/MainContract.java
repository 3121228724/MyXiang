package com.example.myapplication;

/**
 * Created by 马振雷 on 2018/1/31.
 */

public class MainContract {

    interface View extends BaseView{

        void initViews();

        void setAdapter(MyAdapter adapter);

        void loadCompleted();

    }

    interface Presenter extends BasePresenter<View>{

        void initData();

        void loadMore();
    }
}
