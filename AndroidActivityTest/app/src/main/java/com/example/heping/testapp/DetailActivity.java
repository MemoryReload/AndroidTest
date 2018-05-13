package com.example.heping.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    static final int RESULTCODE_DETAIL=54321;
    static final String PARAM_KEY="from";
    static final String RESULT_KEY="result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.getStringExtra(DetailActivity.PARAM_KEY)!=null) Toast.makeText(this,intent.getStringExtra(PARAM_KEY),Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_detail);
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent1 = new Intent(DetailActivity.this, MasterActivity.class);
                intent1.putExtra(DetailActivity.PARAM_KEY,"go back to single task master");
                startActivity(intent1);
            };
        });
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_KEY,"go back from detail");
        setResult(RESULTCODE_DETAIL,resultIntent);
        super.onBackPressed();
    }
}
