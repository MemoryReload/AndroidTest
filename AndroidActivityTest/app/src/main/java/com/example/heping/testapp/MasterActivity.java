package com.example.heping.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MasterActivity extends AppCompatActivity {

    static final int REQUESTCODECODE_DETAIL = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        final Button btn1 =(Button)this.findViewById(R.id.button1);
        final Button bt2 = findViewById(R.id.button2);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Hello World!",Toast.LENGTH_SHORT).show();
                Intent intent=null;
                if (v.getId()==R.id.button1){
                    intent = new Intent(v.getContext(),DetailActivity.class);
                    intent.putExtra(DetailActivity.PARAM_KEY,"jump from an explicit intent");
                }
                else if (v.getId()==R.id.button2){
                    intent = new Intent(DetailActivity.ACTION_DETAIL);
                    intent.putExtra(DetailActivity.PARAM_KEY,"jump from an implicit intent");
                }
                startActivityForResult(intent,REQUESTCODECODE_DETAIL);
            }
        };
        btn1.setOnClickListener(listener);
        bt2.setOnClickListener(listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUESTCODECODE_DETAIL&&resultCode==DetailActivity.RESULTCODE_DETAIL){
            Toast.makeText(this, data.getStringExtra(DetailActivity.RESULT_KEY), Toast.LENGTH_SHORT).show();
        }
    }
}
