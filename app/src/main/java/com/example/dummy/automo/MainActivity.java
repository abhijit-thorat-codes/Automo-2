package com.example.dummy.automo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button login;
    QEditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.log);
        etUsername = (QEditText) findViewById(R.id.username);
        etPassword = (QEditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(etUsername.getText().toString().equals(""))
                    etUsername.setError("Required!");
                else if(etPassword.getText().toString().equals(""))
                    etPassword.setError("Required!");
                else if(!etUsername.getText().toString().equals("automo") || !etPassword.getText().toString().equals("12345"))
                     Toast.makeText(MainActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                else
                {
                    Intent i=new Intent(MainActivity.this,Panel.class);
                    startActivity(i);
                }
            }
        });
    }
}


