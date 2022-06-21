package com.example.product_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

    private RecyclerView tweetsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        setContentView(R.layout.activity_main);

        getDishesData();
    }

    private void getDishesData() {
        String baseUrl = "https://food.madskill.ru/dishes?version=1.01";

        JSONArray json = new JSONArray();
        try {
            json.put(new JSONObject().put("version", 1.01));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, baseUrl, null,
                response ->
                {
                    addDishesData(response);
                    initRecyclerView();
                },
                error -> Toast.makeText(this, "Не удалось получить данные с сервера.", Toast.LENGTH_LONG).show());
        requestQueue.add(request);
    }

    private void addDishesData(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();

        String nameDish = "";
        String iconResource = "";
        int price = 0;
        int count = 0;

        String baseImageUrl = "https://food.madskill.ru/up/images/";

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
                nameDish = jsonObject.getString("nameDish");
                iconResource = jsonObject.getString("icon");
                price = Integer.parseInt(jsonObject.getString("price"));
                count++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            menuItems.add(new MenuItem(nameDish, price, baseImageUrl.concat(iconResource), count));
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.list);
        MenuItemAdapter adapter = new MenuItemAdapter(this, menuItems);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(null);
        recyclerView.getRecycledViewPool().clear();
        recyclerView.swapAdapter(adapter, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }
}