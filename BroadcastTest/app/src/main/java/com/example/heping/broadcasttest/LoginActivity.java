package com.example.heping.broadcasttest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends BaseActivity implements TextView.OnEditorActionListener, View.OnClickListener, View.OnFocusChangeListener, Handler.Callback {
    private View layout;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginBtn;
    private CheckBox rememberBtn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private  static String ACCOUNT = "account";
    private  static String PASSWD = "password";
    private  static String REMEMBER = "remember";
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
        rememberBtn = findViewById(R.id.rememberBtn);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean checked = sharedPreferences.getBoolean(REMEMBER,false);
        if (checked) {
            rememberBtn.setChecked(true);
            String account = sharedPreferences.getString(ACCOUNT,"");
            String password = sharedPreferences.getString(PASSWD,"");
            accountEdit.setText(account);
            passwordEdit.setText(password);
        }
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
        Handler handler = new Handler(Looper.getMainLooper(),this);
        Message msg = handler.obtainMessage();
        if (account.contentEquals("admin") && password.contentEquals("admin")){
            Bundle params = new Bundle();
            params.putString(ACCOUNT,account);
            params.putString(PASSWD,password);
            msg.what = 1;
            msg.setData(params);
        }else{
            Bundle params = new Bundle();
            params.putString("msg","Login failed!");
            msg.what = 0;
            msg.setData(params);
        }
        handler.sendMessage(msg);
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
        switch(message.what){
            case 1:
                loginSuccess(message);
                break;
            case 0:
                loginFailed(message);
                break;

        }
        return false;
    }

    private  void  loginSuccess(Message message)
    {
        //save the account and password
        editor = sharedPreferences.edit();
        if (rememberBtn.isChecked()){
            editor.putBoolean(REMEMBER,true);
            Bundle params = message.getData();
            String account = params.getString(ACCOUNT);
            String password = params.getString(PASSWD);
            editor.putString(ACCOUNT,account);
            editor.putString(PASSWD,password);
        }else {
            editor.clear();
        }
        editor.apply();
        MainActivity.start(this);
        finish();
    }

    private  void  loginFailed(Message message)
    {

    }
}
