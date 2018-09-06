package com.example.heping.fragmenttest;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private List<MyFragment> fragments;
    private RadioGroup tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabBar = findViewById(R.id.tab_bar);
        initFragments();
        tabBar.setOnCheckedChangeListener(this);
        tabBar.check(R.id.item0);
    }

    private void initFragments(){
        int count = tabBar.getChildCount();
        fragments = new ArrayList<MyFragment>();
        for (int i=0 ; i<count; i++){
            MyFragment fragment = new MyFragment();
            fragments.add(i,fragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = this.tabBar.indexOfChild(this.tabBar.findViewById(checkedId));
        showFragmentAtIndex(index);
    }

    private void showFragmentAtIndex(int index)
    {
        Fragment newFragment = fragments.get(index);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, newFragment);
        transaction.commit();

        String[] colors = {"#FF0000","#00FF00","#0000FF","#FF5A00"};
        View view= newFragment.getView();
        String color = colors[colors.length%4];
        int c = Color.parseColor(color);
        view.setBackgroundColor(c);
    }
}
