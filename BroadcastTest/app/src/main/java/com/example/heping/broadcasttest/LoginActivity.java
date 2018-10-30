package com.example.heping.broadcasttest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements TextView.OnEditorActionListener, View.OnClickListener, View.OnFocusChangeListener, Handler.Callback {
    private View layout;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
        layout = findViewById(R.id.layout);
        layout.setOnFocusChangeListener(this);
        accountEdit = findViewById(R.id.account);
        accountEdit.setOnEditorActionListener(this);
        passwordEdit = findViewById(R.id.password);
        passwordEdit.setOnEditorActionListener(this);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
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
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
            login(accountEdit.getText().toString(), passwordEdit.getText().toString(), this);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        if (TextUtils.isEmpty(accountEdit.getText())){
            Toast.makeText(this,"账号不能为空！",Toast.LENGTH_SHORT).show();
            return ;
        }else if (TextUtils.isEmpty(passwordEdit.getText())){
            Toast.makeText(this,"密码不能为空！",Toast.LENGTH_SHORT).show();
            return ;
        }
        login(accountEdit.getText().toString(), passwordEdit.getText().toString(), this);
    }

    public void login(String account, String password,Handler.Callback callback){
        Log.d("Login", "account= "+accountEdit.getText()+"password= "+passwordEdit.getText());
        //TODO: user identity authenticate operation
        Handler handler = new Handler(this);
        handler.sendMessage(handler.obtainMessage(1));
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b){
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    @Override
    public boolean handleMessage(Message message) {
        //保存账户密码
        MainActivity.start(this);
        return false;
    }
}
