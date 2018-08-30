package com.example.heping.listviewtest;

import com.example.heping.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits;
    private ListView fruitListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fruitListView = findViewById(R.id.fruitList);
        this.fruitListView.setVerticalScrollBarEnabled(false);
        this.fruitListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.list_item, fruits);
        this.fruitListView.setAdapter(adapter);
        this.fruitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,fruits.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initFruits()
            {
                fruits = new ArrayList<Fruit>();
                for (int i=0; i<30; i++)
                {
                    Fruit fruit = null;
                    if (i % 2 == 0 ){
                        fruit = new Fruit(R.drawable.apple,"Apple");
                    }
                    else {
                        fruit = new Fruit("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000" +
                                "&sec=1535231654389&di=0b05a5819b76c2dc9dbe345e63c71c6e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu."+
                                "com%2Fimgad%2Fpic%2Fitem%2F80cb39dbb6fd52668bf47edda118972bd507361f.jpg","Pear");
                    }
                    this.fruits.add(fruit);
                }
    }
}

class FruitAdapter extends ArrayAdapter<Fruit>{

    private int mItemResource;
    private Handler handler;

    static class ViewHolder extends  Object{
        public ImageView iconView;
        public TextView  nameView;
    }

    public FruitAdapter(Context context, @LayoutRes int resource, List<Fruit> objects)
    {
        super(context, resource, objects);
        this.mItemResource = resource;
        this.handler = new Handler();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView;
        final ViewHolder viewHolder;
        if (convertView != null) {
            listItemView = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            listItemView = LayoutInflater.from(getContext()).inflate(mItemResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iconView = listItemView.findViewById(R.id.icon);
            viewHolder.nameView = listItemView.findViewById(R.id.text);
            listItemView.setTag(viewHolder);
        }
        final Fruit item = getItem(position);
        viewHolder.nameView.setText(item.getName());
        if (item.getImageURL() != null){
            if (item.getImageCache() != null)
            {
                viewHolder.iconView.setImageBitmap(item.getImageCache());
            }
            else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = ImageLoader.getBitmapFromURL(item.getImageURL());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                item.setImageCache(bitmap);
                                viewHolder.iconView.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        }
        else {
            viewHolder.iconView.setImageResource(item.getImageResource());
        }
        return listItemView;
    }
}


