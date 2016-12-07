package com.example.sorina.login.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sorina.login.R;

import java.util.Timer;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private TextView usernameError;
    private TextView passwordError;
    private TextView loginError;
    private Button signIn;
    private RelativeLayout loginLayout;
    private RelativeLayout spinnerLayout;
    private CountDownTimer progressTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Books Store App");
        setContentView(R.layout.activity_login);
        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(handleClick);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.icon);

        progressTimer = new	CountDownTimer(10000, 1000) {
            @Override
            public	void onTick(long	millisUntilFinished){
                loginLayout.setVisibility(View.GONE);
                spinnerLayout.setVisibility(View.VISIBLE);
                setTitle("Books List");
                System.out.println("Seconds remaining: " + millisUntilFinished / 1000);
            }
            @Override
            public	void onFinish() {
                spinnerLayout.setVisibility(View.GONE);
                startActivity(new Intent(LoginActivity.this, BooksListActivity.class));
            }
        };

        loginLayout = (RelativeLayout)findViewById(R.id.login_layout);
        spinnerLayout = (RelativeLayout)findViewById(R.id.spinner_layout);
        loginLayout.setVisibility(View.VISIBLE);
        spinnerLayout.setVisibility(View.GONE);
    }

    private View.OnClickListener handleClick = new View.OnClickListener(){
        public void onClick(View arg0) {
            usernameText = (EditText) findViewById(R.id.usernameText);
            passwordText = (EditText) findViewById(R.id.passwordText);
            usernameError = (TextView) findViewById(R.id.usernameError);
            passwordError = (TextView) findViewById(R.id.passwordError);
            loginError = (TextView) findViewById(R.id.loginError);

            usernameError.setText("");
            passwordError.setText("");
            loginError.setText("");
            usernameError.setTextColor(Color.RED);
            passwordError.setTextColor(Color.RED);
            loginError.setTextColor(Color.RED);

            if (usernameText.getText().length() == 0) {
                usernameError.setText("Username cannot be empty!");
            } else if (usernameText.getText().length() < 4) {
                usernameError.setText("Username is too short!");
            }

            if (passwordText.getText().length() == 0) {
                passwordError.setText("Password cannot be empty!");
            } else if (passwordText.getText().length() < 6) {
                passwordError.setText("Password is too short!");
            }


            if (usernameError.getText().equals("") && passwordError.getText().equals("")) {
                if (!usernameText.getText().toString().equals("admin") || !passwordText.getText().toString().equals("password")) {
                    loginError.setText("Login failed. Username or password is incorrect!");
                    usernameText.setText("");
                    passwordText.setText("");
                } else {
                    loginError.setTextColor(Color.GREEN);
                    loginError.setText("Login succesful!");
                    progressTimer.start();
                }
            }
        }
    };

}

