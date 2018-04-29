package com.example.heping.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    static final String ACTION_DETAIL="com.example.heping.testapp.start_detail";
    static final int RESULTCODE_DETAIL=54321;
    static final String PARAM_KEY="from";
    static final String RESULT_KEY="result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Toast.makeText(this,intent.getStringExtra(PARAM_KEY),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_KEY,"go back from detail");
        setResult(RESULTCODE_DETAIL,resultIntent);
        super.onBackPressed();
    }
}
