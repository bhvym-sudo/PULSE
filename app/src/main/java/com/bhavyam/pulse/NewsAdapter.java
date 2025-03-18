package com.bhavyam.pulse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsItem> newsItems;

    
    public NewsAdapter(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        
        NewsItem item = newsItems.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.regionTextView.setText(item.getRegion());
        holder.descriptionTextView.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, regionTextView, descriptionTextView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            
            titleTextView = itemView.findViewById(R.id.newsTitle);
            regionTextView = itemView.findViewById(R.id.newsRegion);
            descriptionTextView = itemView.findViewById(R.id.newsDescription);
        }
    }
}