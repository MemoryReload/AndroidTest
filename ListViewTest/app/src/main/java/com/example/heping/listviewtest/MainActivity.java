package com.example.heping.listviewtest;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits = new ArrayList<>();
    private ListView fruitListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fruitListView = findViewById(R.id.fruitList);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.list_item, fruits);
        this.fruitListView.setAdapter(adapter);
    }

    protected void initFruits()
    {
        for (int i=0; i<30; i++)
        {
            Fruit fruit = new Fruit(R.drawable.pen,"Apple");
            this.fruits.add(fruit);
        }
    }
}

class Fruit extends Object {

    private int     mImageResource;
    private String  mName;

    public Fruit(@DrawableRes int image, String name)
    {
        this.mImageResource = image;
        this.mName = name;
    }

    public String getName()
    {
        return this.mName;
    }

    public int getImage()
    {
        return this.mImageResource;
    }
}

class FruitAdapter extends ArrayAdapter<Fruit>{

    private int mItemResource;

    public FruitAdapter(Context context, @LayoutRes int resource, List<Fruit> objects)
    {
        super(context, resource, objects);
        this.mItemResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = LayoutInflater.from(getContext()).inflate(mItemResource,parent,false);
        Fruit item = getItem(position);
        ImageView iconView = listItemView.findViewById(R.id.icon);
        TextView  text = listItemView.findViewById(R.id.text);
        iconView.setImageResource(item.getImage());
        text.setText(item.getName());
        return listItemView;
    }
}