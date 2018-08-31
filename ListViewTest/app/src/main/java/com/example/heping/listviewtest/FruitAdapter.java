package com.example.heping.listviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heping.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int mItemResource;
    private Handler handler;

    static class ViewHolder extends  Object{
        public ImageView iconView;
        public TextView nameView;
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
