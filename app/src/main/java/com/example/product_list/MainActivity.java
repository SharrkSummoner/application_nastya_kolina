package com.example.product_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        Intent intent = new Intent(this, LogInActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }
}