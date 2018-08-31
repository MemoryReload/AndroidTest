package com.example.heping.listviewtest;

import com.example.heping.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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


