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

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ArrayList<Fragment> fragments;
    private Fragment currentFragment;
    private RadioGroup tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabBar = findViewById(R.id.tab_bar);
        tabBar.setOnCheckedChangeListener(this);
        initFragments();
        tabBar.check(R.id.item0);
    }

    private void initFragments()
    {
        currentFragment = null;
        fragments = new ArrayList<Fragment>(tabBar.getChildCount());
        for (int i=0; i<tabBar.getChildCount(); i++){
            fragments.add(null);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = this.tabBar.indexOfChild(this.tabBar.findViewById(checkedId));
        showFragmentAtIndex(index);
    }

    private void showFragmentAtIndex(int index)
    {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragmentToShow = fragments.get(index);
        FragmentTransaction transaction = manager.beginTransaction();
        if (currentFragment != null)
        {
            transaction.hide(currentFragment);
        }
        if(fragmentToShow == null) {
            fragmentToShow = new MyFragment();
            String[] colors = {"#FF0000","#00FF00","#0000FF","#FF5A00"};
            String color = colors[index%colors.length];
            Bundle bundle = new Bundle();
            bundle.putString("color",color);
            fragmentToShow.setArguments(bundle);
            transaction.add(R.id.fragment, fragmentToShow);
        }
        transaction.show(fragmentToShow).commit();
    }
}
