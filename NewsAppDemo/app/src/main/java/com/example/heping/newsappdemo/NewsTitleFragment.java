package com.example.heping.newsappdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    public boolean isTowPane;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_title_frag,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.news_title_recycler_view);
        NewsAdapter adapter = new NewsAdapter(getNews());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return  view;
    }
    
    private List<News> getNews()
    {
        List<News> newsList = new ArrayList<News>();
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.setTitle("This is news title"+i);
            news.setContent(getRandomLengthContent("This is news content"+i+"."));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content)
    {
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null){
            isTowPane = true;
        }
        else {
            isTowPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
    {
        private List<News> mNewsList;

        public NewsAdapter(List<News> news)
        {
            mNewsList = news;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView titleView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                titleView = itemView.findViewById(R.id.news_title);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item,viewGroup,false);
            final  ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTowPane == true){
                        NewsContentFragment contentFragment = (NewsContentFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
                        contentFragment.refresh(news.getTitle(),news.getContent());
                    }
                    else {
                        NewsContentActivity.startAction(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            News item = mNewsList.get(i);
            viewHolder.titleView.setText(item.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}
