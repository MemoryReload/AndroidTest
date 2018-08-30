package com.example.heping.recyclelisttest;

import com.example.heping.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.heping.listviewtest.Fruit;
import com.example.heping.listviewtest.ImageLoader;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyleListActivity extends AppCompatActivity {
    private List<Fruit> fruits;
    private RecyclerView recycleList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_list);
        this.recycleList = findViewById(R.id.fruitRecycleList);
        initFruits();
        RecycleListAdapter adapter = new RecycleListAdapter(fruits);
        LinearLayoutManager layoutManager = new LinearLayoutManager(RecyleListActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.recycleList.setAdapter(adapter);
        this.recycleList.setLayoutManager(layoutManager);
    }

    protected void initFruits()
    {
        fruits = new ArrayList<Fruit>();
        for (int i=0; i<30; i++)
        {

            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 1; j < new Random().nextInt(100); j++){
                stringBuilder.append("text");
            }
            String name = stringBuilder.toString();

            Fruit fruit = null;
            if (i % 2 == 0 ){
                fruit = new Fruit(R.drawable.apple, name);
            }
            else {
                fruit = new Fruit("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000" +
                        "&sec=1535231654389&di=0b05a5819b76c2dc9dbe345e63c71c6e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu."+
                        "com%2Fimgad%2Fpic%2Fitem%2F80cb39dbb6fd52668bf47edda118972bd507361f.jpg",name);
            }
            fruits.add(fruit);
        }
    }
}

class ViewHolder extends RecyclerView.ViewHolder
{
    public  final ImageView iconView;
    public  final TextView  nameView;

    public ViewHolder(View itemView)
    {
        super(itemView);
        iconView = this.itemView.findViewById(R.id.icon);
        nameView = this.itemView.findViewById(R.id.name);
    }
}

 class RecycleListAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private   List<Fruit>    mFruits;
    private   Handler        handler;

    public RecycleListAdapter(List<Fruit> fruits)
    {
        this.mFruits = fruits;
        this.handler = new Handler();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Fruit item = mFruits.get(position);
        holder.nameView.setText(item.getName());
        if (item.getImageURL() != null){
            if (item.getImageCache() != null)
            {
                holder.iconView.setImageBitmap(item.getImageCache());
            }
            else {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Bitmap bitmap = ImageLoader.getBitmapFromURL(item.getImageURL());
                        item.setImageCache(bitmap);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                holder.iconView.setImageBitmap(item.getImageCache());
                            }
                        });
                    }
                }).start();
            }
        }
        else{
            holder.iconView.setImageResource(item.getImageResource());
        }
    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }
}
