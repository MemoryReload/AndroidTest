package com.example.heping.recyclelisttest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heping.R;
import com.example.heping.listviewtest.Fruit;

import java.util.List;

public class StaggeredGridListActivity extends RecyleListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        StaggerGridAdapter adapter = new StaggerGridAdapter(this.fruits);
        this.recycleList.setLayoutManager(manager);
        this.recycleList.setAdapter(adapter);
    }
}

class StaggerGridAdapter extends RecycleListAdapter
{

    public StaggerGridAdapter(List<Fruit> fruits) {
        super(fruits);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.stagger_grid_item,parent,false);
        return new ViewHolder(item);
    }
}