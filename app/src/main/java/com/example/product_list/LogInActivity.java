package com.example.product_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void login(View view) {
        if ((email.getText().toString().isEmpty()) || password.getText().toString().isEmpty()) {
            createDialog(this, "Не все поля заполнены");
        } else if (!chekEmail(email.getText().toString())) {
            createDialog(this, "Некорректно введена почта");
        } else {
            tryLogin();
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        LogInActivity.this.startActivity(intent);
        LogInActivity.this.finish();
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        LogInActivity.this.startActivity(intent);
        LogInActivity.this.finish();
    }

    private boolean chekEmail(String mail) {
        return mail.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,3})$");
    }

    public void tryLogin() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", mail);
            jsonObject.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "https://food.madskill.ru/auth/login";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                response -> {
                    Intent intent = new Intent(this, list.class);
                    LogInActivity.this.startActivity(intent);
                    LogInActivity.this.finish();
                },
                error -> createDialog(this, "Неверная почта или пароль"));
        requestQueue.add(request);
    }


    public void createDialog(Activity activity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle("Error")
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.create().show();
    }
}