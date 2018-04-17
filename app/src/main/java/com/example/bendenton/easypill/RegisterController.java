package com.example.bendenton.easypill;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterController extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
        Toast.makeText(this, "Please Register", Toast.LENGTH_LONG).show();
    }

    public void touchLoginReg(View view) {
        startActivity(new Intent(RegisterController.this, LoginController.class));
    }

    public void touchRegisterReg(View view) {
        String username = ((EditText)findViewById(R.id.txtRegUsername)).getText().toString();
        String password = ((EditText)findViewById(R.id.txtRegPassword)).getText().toString();
        if (!username.matches("") || !password.matches("")) {
            UserModel user = new UserModel(username, password);
            DatabaseHelper db = new DatabaseHelper(this);
            Boolean added = db.insertUser(user);
            if (added) {
                Toast.makeText(this, "User "+ username +" Added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_LONG).show();
        }
    }
}
