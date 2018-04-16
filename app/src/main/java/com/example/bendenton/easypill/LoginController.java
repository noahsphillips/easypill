package com.example.bendenton.easypill;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginController
{
    public void touchLogin(View view) {
        EditText username = (EditText) findViewById(R.id.txtUsername);
        EditText password =  (EditText) findViewById(R.id.txtPassword);
        if(username.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equals("ungadmin"))
        {
            Uri loginPage = Uri.parse("https://www.ung.edu");
            Intent intent = new Intent(Intent.ACTION_VIEW, loginPage);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_LONG).show();
        }
}
