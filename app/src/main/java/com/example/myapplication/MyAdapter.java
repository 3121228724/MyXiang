package com.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 *
 * Created by 马振雷 on 2018/1/31.
 */

public class MyAdapter extends RecyclerView.Adapter {

    private List<String>  strings;


     MyAdapter(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView bookname;

         ViewHolder(View view) {
            super(view);
            bookname =  view.findViewById(R.id.book_name);
        }
    }

}
