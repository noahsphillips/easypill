package com.example.bendenton.easypill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginController extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        Toast.makeText(this, "Please Log In", Toast.LENGTH_LONG).show();
    }

    public void touchLogin(View view) {
        EditText username = (EditText) findViewById(R.id.txtUsername);
        EditText password =  (EditText) findViewById(R.id.txtPassword);
        if (!username.getText().toString().matches("") || !password.getText().toString().matches("")) {
            UserModel user = new UserModel(username.getText().toString(), password.getText().toString());
            DatabaseHelper db = new DatabaseHelper(this);
            Boolean loggedIn = db.getLoginInfo(user);
            if(loggedIn)
            {
                startActivity(new Intent(LoginController.this, PillsController.class));
            }
            else
            {
                Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_LONG).show();
        }
    }

    public void touchRegister(View view) {
        startActivity(new Intent(LoginController.this, RegisterController.class));
    }
}
