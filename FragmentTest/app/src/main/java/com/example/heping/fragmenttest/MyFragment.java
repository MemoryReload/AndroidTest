package com.example.heping.fragmenttest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

interface MyFragmentCallback{
    void onClickFragment(Fragment fragment);
}

public class MyFragment extends Fragment {
    private MyFragmentCallback callback;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment,container,false);
        String color = getArguments().getString("color");
        myView.setBackgroundColor(Color.parseColor(color));
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) callback.onClickFragment(MyFragment.this);
            }
        });
        return myView;
    }

    public void setCallback(MyFragmentCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.callback = null;
    }
}
