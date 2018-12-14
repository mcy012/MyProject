package com.example.eda1707.movie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;

    List<MovieInfo> items = new ArrayList<>();

    OnItemClickListener listener;

    public static interface OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);
    }

    public MovieAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.movie_item, viewGroup ,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MovieInfo item = items.get(i);
        viewHolder.setItem(item);

        viewHolder.setOnItemClickListener(listener);
    }

    public MovieInfo getItem(int position){
        return items.get(position);

    }

    public void addItem(MovieInfo item){
        items.add(item);
    }

    public void addItems(List<MovieInfo> items) {
        this.items.addAll(items);
        //notifyDataSetChanged();
        notifyItemRangeInserted(this.items.size(), items.size());
    }

    public void clearItems(){
        items.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title)
        TextView titleView;
        @BindView(R.id.pubDate)
        TextView pubDateView;
        @BindView(R.id.director)
        TextView directorView;
        @BindView(R.id.actor)
        TextView actorView;
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.userRating)
        RatingBar userRatingView;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

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

        public void setItem(MovieInfo item) {
            titleView.setText(Html.fromHtml(item.getTitle()));
            userRatingView.setRating(item.getUserRating() / 2);
            pubDateView.setText(Html.fromHtml(item.getPubDate()));
            directorView.setText(Html.fromHtml(item.getDirector()));
            actorView.setText(Html.fromHtml(item.getActor()));
            Glide.with(context).load(item.getImage()).into(imageView);
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
    }
}

