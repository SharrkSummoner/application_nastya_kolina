package com.example.product_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class list extends ListActivity {

    private String[] name = { "Морс", "Макароны", "Борщ",
            "Стейк из свинины", "Молочный коктейль", "Картошка фри",
            "Наггетсы", "Чай", "Шаурма с курицей",
            "Солянка", "Бараньи ребрышки", "Пирог с вишней"};
    private String[] price = {"200", "400", "130",
            "1200", "200", "100",
            "120", "60", "360",
            "310", "790", "140"};
    int[] images = {R.drawable.mors, R.drawable.b5c63d06, R.drawable.borch,
            R.drawable.steik, R.drawable.milk, R.drawable.fri,
            R.drawable.naggets, R.drawable.tea, R.drawable.shutterstock_1122622892,
            R.drawable.recept_4986_jtf2, R.drawable.rebra, R.drawable.b4665104};

    private resAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = (ListView) findViewById(R.id.listview);

        adapter = new resAdapter(this);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String selection = adapter.getString(position);
        Toast.makeText(this, selection, Toast.LENGTH_LONG).show();
    }

    private class resAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;

        resAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = mLayoutInflater.inflate(R.layout.list_item, null);

            ImageView image = (ImageView) convertView.findViewById(R.id.dish_icon);
            image.setImageResource(images[position]);

            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);

            nameTextView.setText(name[position]);
            TextView priceTextView = (TextView) convertView.findViewById(R.id.price);

            priceTextView.setText(price[position]);
            return convertView;
        }

        String getString(int position) {
            return name[position] + " (" + price[position] + ")";
        }
    }
}