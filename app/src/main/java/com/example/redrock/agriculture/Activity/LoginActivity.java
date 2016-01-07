package com.example.redrock.agriculture.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redrock.agriculture.Presenter.LoginPresenter;
import com.example.redrock.agriculture.R;

import java.net.PasswordAuthentication;

public class LoginActivity extends Activity implements View.OnClickListener, LoginPresenter.LoginView {

    private TextView name;
    private TextView password;
    private LoginPresenter presenter;

    @Override
    public void loginError(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView login_ensure = (TextView) findViewById(R.id.login_ensure);
        login_ensure.setOnClickListener(this);
        name = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_ensure:
                CharSequence un = name.getText();
                CharSequence pw = password.getText();
                presenter.login(un , pw);
                break;
        }
    }

}
