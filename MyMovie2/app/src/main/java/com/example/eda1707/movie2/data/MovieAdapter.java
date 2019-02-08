package com.example.eda1707.movie2.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.eda1707.movie2.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static Context context;

    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void onItemClick(ViewHolder holder, View view, int i);
    }

    ArrayList<MovieDetailInfo> items = new ArrayList<MovieDetailInfo>();

    public MovieAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.movie_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MovieDetailInfo item =  items.get(i);
        viewHolder.setItem(item);

        viewHolder.setOnItemClickListener(listener);
    }

    public void addItem(MovieDetailInfo item) {
        items.add(item);
    }

    public void addItems(ArrayList<MovieDetailInfo> items) {
        this.items = items;
    }

    public MovieDetailInfo getItem(int i) {
        return items.get(i);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        PhotoView photoView;
        MovieDetailInfo movieDetailInfo;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photoView = (PhotoView) itemView.findViewById(R.id.photoView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(MovieDetailInfo item) {
            String photos = item.getPhotos();
            String[] photoArray = photos.split(",");

            for(int i = 0; i<photoArray.length; i++) {
                Glide.with(context).load(photoArray).into(photoView);
            }
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

}
