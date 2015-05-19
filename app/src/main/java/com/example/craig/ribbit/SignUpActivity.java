package com.example.craig.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class SignUpActivity extends Activity {

    @InjectView(R.id.usernameField) EditText mUsernameField;
    @InjectView(R.id.passwordField) EditText mPasswordField;
    @InjectView(R.id.emailField) EditText mEmailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.signUpButton)
    public void onSignUpButtonClick(){
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();
        String email = mEmailField.getText().toString();

        username = username.trim();
        password = password.trim();
        email = email.trim();

        //alertdialog
        if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.signup_error_message)
                .setTitle(R.string.signup_error_title)
                .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else{
            //create new user!
            ParseUser newUser = new ParseUser();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        //success
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else{
                        //failure!
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                        builder.setMessage(e.getMessage())
                                .setTitle(R.string.signup_error_title)
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
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
