package com.example.heping.recyclelisttest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.heping.R;
import com.example.heping.listviewtest.Fruit;

import java.util.List;

public class StaggeredGridListActivity extends RecyleListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        StaggerGridAdapter adapter = new StaggerGridAdapter(this.fruits);
        this.recycleList.setLayoutManager(manager);
        this.recycleList.setAdapter(adapter);
    }
}

class StaggerGridAdapter extends RecycleListAdapter
{

    public StaggerGridAdapter(List<Fruit> fruits) {
        super(fruits);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.stagger_grid_item,parent,false);
//        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.stagger_grid_item_linear,parent,false);
        final ViewHolder viewHolder = new ViewHolder(item);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                showDialogForDeletePosition(v.getContext(), position);
            }
        });
        return viewHolder;
    }

    private void showDialogForDeletePosition(Context context, final int position)
    {
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle("警 告")
                .setMessage("您确定要删除这条信息吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFruits.remove(position);
                notifyItemRemoved(position);
            }
        })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

//    @Override
//    public void onViewAttachedToWindow(@NonNull final ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        // There is some thing bugly!
//        holder.iconView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                holder.iconView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                ViewGroup.LayoutParams layoutParams = holder.iconView.getLayoutParams();
//                layoutParams.width = holder.iconView.getWidth();
//                layoutParams.height = holder.iconView.getWidth();
//                holder.iconView.setLayoutParams(layoutParams);
//            }
//        });
//    }
}