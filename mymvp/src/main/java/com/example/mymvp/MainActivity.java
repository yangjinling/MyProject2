package com.example.mymvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mymvp.bean.User;
import com.example.mymvp.presenter.LoginPresent;
import com.example.mymvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private Button login;
    private Button clear;
    private EditText name;
    private EditText pass;
    private ProgressBar prograss;
    private LoginPresent mLoginPresent=new LoginPresent(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    public void findView() {
        login = ((Button) findViewById(R.id.login));
        clear = ((Button) findViewById(R.id.clear));
        name = ((EditText) findViewById(R.id.et_name));
        pass = ((EditText) findViewById(R.id.et_pass));
        prograss = ((ProgressBar) findViewById(R.id.prograss));
        login.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getPass() {
        return pass.getText().toString();
    }

    @Override
    public void clearName() {
        name.setText("");
    }

    @Override
    public void clearPass() {
        pass.setText("");
    }

    @Override
    public void toActivity(User user) {
        Toast.makeText(this,"姓名："+user.getName()+"密码："+user.getPassword(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoging() {
        prograss.setVisibility(View.VISIBLE);

    }

    @Override
    public void hindLoging() {
        prograss.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,"用户名或者密码错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                mLoginPresent.login();
                break;
            case R.id.clear:
                mLoginPresent.clear();
                break;
        }
    }
}
