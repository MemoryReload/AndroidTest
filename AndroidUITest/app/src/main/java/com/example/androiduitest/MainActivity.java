package com.example.androiduitest;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;

    private int mResource;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.activity_main_button);
        btn.setOnClickListener(this);
        editText = findViewById(R.id.activity_main_editText);
        mResource = R.drawable.dog_lord;
        imageView = findViewById(R.id.activity_main_imageView);
        imageView.setImageResource(mResource);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_button:
//                if (mResource == R.drawable.dog_lord){
//                    mResource = R.drawable.dog_and_cat;
//                }
//                else {
//                    mResource = R.drawable.dog_lord;
//                }
//                imageView.setImageResource(mResource);

//                String inputText = editText.getText().toString();
//                Toast.makeText(this,inputText,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("This is a Dialog.");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
                default:
                    break;
        }
    }
}
