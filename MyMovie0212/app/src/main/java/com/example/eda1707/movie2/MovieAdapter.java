package com.example.eda1707.movie2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.eda1707.movie2.data.PhotoInfoDto;
import com.example.eda1707.movie2.data.PhotoType;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    static Context context;

    List<PhotoInfoDto> photoUrlList = new ArrayList<>();

    OnItemClickListener listener;

    RelativeLayout playImage;

    public static interface OnItemClickListener {
        public void onItemClick(ViewHolder holder, View view, int position);
    }

    /**
     * MovieDetailFrament에서 adapter로 넘겨준 값을 조종하기, photoUrls랑 videoUrls로 받았다.
     * @param context
     * @param photoUrls
     * @param videoUrls
     */
    public MovieAdapter(Context context, String photoUrls, String videoUrls) {
        this.context = context;

        if(photoUrls != null) {
            for(String photoUrl : photoUrls.split(",")) {
                photoUrlList.add(new PhotoInfoDto(photoUrl, PhotoType.IMAGE));
            }
        }

        if(videoUrls != null) {
            String videoUrl[] = videoUrls.split(",");

            for (int index = 0; index < videoUrl.length; index++) {

                //photoUrlList.addAll(Arrays.asList("http://img.youtube.com/vi" + videoThumb[index].substring(videoThumb[index].lastIndexOf("/")) + "/0.jpg"));
                //photoUrlList.add(new PhotoInfoDto("http://img.youtube.com/vi" + videoUrl[index].substring(videoUrl[index].lastIndexOf("/")) + "/0.jpg", videoUrl[index], PhotoType.VIDEO));
                photoUrlList.add(new PhotoInfoDto(String.format("http://img.youtube.com/vi%s/0.jpg", videoUrl[index].substring(videoUrl[index].lastIndexOf("/"))), videoUrl[index], PhotoType.VIDEO));
            }
        }
    }

    @Override
    public int getItemCount() {
        return photoUrlList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.movie_item, viewGroup,false);

        playImage = (RelativeLayout) itemView.findViewById(R.id.playImage);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PhotoInfoDto item = photoUrlList.get(i);

        Log.i("ganzi", item.toString());

        if(item.getType() == PhotoType.VIDEO) {
            playImage.setVisibility(View.VISIBLE);
        }

        viewHolder.setItem(item);

        viewHolder.setOnItemClickListener(listener);
    }

    /*public void addItem(String photoUrl) {
        photoUrlList.add(photoUrl);
    }
    public void addItems(List<String> photoList) {
        this.photoUrlList = photoList;
    }*/

    public PhotoInfoDto getItem(int position) {
        return photoUrlList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryView;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            galleryView = (ImageView) itemView.findViewById(R.id.galleryView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positon = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, v, positon);
                    }
                }
            });
        }

        public void setItem(PhotoInfoDto item) {
            Glide.with(context).load(item.getImgUrl()).into(galleryView);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
}
