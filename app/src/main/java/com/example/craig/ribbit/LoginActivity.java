package com.example.craig.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends Activity {


    @InjectView(R.id.usernameField) EditText mUsernameField;
    @InjectView(R.id.passwordField) EditText mPasswordField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.signUpTextView)
    public void startSignUpActivity(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.loginButton)
    public void onSignUpButtonClick(){
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();

        username = username.trim();
        password = password.trim();

        //alertdialog
        if(username.isEmpty() || password.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.login_error_message)
                    .setTitle(R.string.login_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else{
            // Login
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if(e == null) {
                        //Success!
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage(e.getMessage())
                                .setTitle(R.string.login_error_title)
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
