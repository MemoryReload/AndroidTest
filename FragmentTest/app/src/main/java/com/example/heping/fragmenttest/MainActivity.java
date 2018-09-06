package com.example.heping.fragmenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }
}
