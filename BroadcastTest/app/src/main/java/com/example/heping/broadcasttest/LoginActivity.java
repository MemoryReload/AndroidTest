package com.example.heping.broadcasttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements TextView.OnEditorActionListener {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
        accountEdit = findViewById(R.id.account);
        accountEdit.setOnEditorActionListener(this);
        passwordEdit = findViewById(R.id.password);
        passwordEdit.setOnEditorActionListener(this);
        loginBtn = findViewById(R.id.loginBtn);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v == accountEdit &&  actionId == EditorInfo.IME_ACTION_NEXT){
            if (TextUtils.isEmpty(accountEdit.getText())){
                Toast.makeText(this, "账号不能为空！", Toast.LENGTH_SHORT).show();
                return true;
            }
        }else if (v == passwordEdit && actionId == EditorInfo.IME_ACTION_GO){
            if (TextUtils.isEmpty(accountEdit.getText())){
                Toast.makeText(this,"账号不能为空！",Toast.LENGTH_SHORT).show();
                return true;
            }else if (TextUtils.isEmpty(passwordEdit.getText())){
                Toast.makeText(this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                return true;
            }
            login(accountEdit.getText().toString(),passwordEdit.getText().toString());
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            return true;
        }
        return false;
    }

    public void login(String account, String password){
        Log.d("Login", "account= "+accountEdit.getText()+"password= "+passwordEdit.getText());
    }
}
