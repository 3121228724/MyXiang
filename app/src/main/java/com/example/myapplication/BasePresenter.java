package com.example.myapplication;

/**
 * Created by jiangkang on 2017/2/17.
 * description：MVP之基Presenter
 */

public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView(T view);
}
