package com.example.heping.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private Fragment currentFragment;
    private RadioGroup tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragment = null;
        tabBar = findViewById(R.id.tab_bar);
        tabBar.setOnCheckedChangeListener(this);
        tabBar.check(R.id.item0);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = this.tabBar.indexOfChild(this.tabBar.findViewById(checkedId));
        showFragmentAtIndex(index);
    }

    private void showFragmentAtIndex(int index)
    {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragmentToShow = manager.findFragmentByTag(String.format("%d",index));
        FragmentTransaction transaction = manager.beginTransaction();
        if (currentFragment != null)
        {
            transaction.hide(currentFragment);
        }
        boolean shouldCommitInmediately = false;
        if(fragmentToShow == null) {
            shouldCommitInmediately = true;
            fragmentToShow = new MyFragment();
            ((MyFragment)fragmentToShow).setCallback(new MyFragmentCallback() {
                @Override
                public void onClickFragment(Fragment fragment) {
                    Toast.makeText(MainActivity.this, fragment.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            String[] colors = {"#FF0000","#00FF00","#0000FF","#FF5A00"};
            String color = colors[index%colors.length];
            Bundle bundle = new Bundle();
            bundle.putString("color",color);
            fragmentToShow.setArguments(bundle);
            transaction.add(R.id.fragment,fragmentToShow, String.format("%d",index));
        }
        transaction.show(fragmentToShow).commit();
        //立即执行事务
        if (shouldCommitInmediately) manager.executePendingTransactions();
        currentFragment = fragmentToShow;
    }
}
