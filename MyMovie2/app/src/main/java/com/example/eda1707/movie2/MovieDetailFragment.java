package com.example.eda1707.movie2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieDetailFragment extends Fragment {

    TextView likeCountView;
    TextView dislikeCountView;
    TextView titleText;
    Button likeButton;
    Button dislikeButton;
    ImageView imageView2, ageImage;
    RatingBar ratingBar;

    boolean likeState = false;
    boolean dislikeState = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.movie_detail_fragment, container, false);

        likeButton = (Button) rootView.findViewById(R.id.likeButton);
        dislikeButton = (Button) rootView.findViewById(R.id.dislikeButton);

        likeCountView = (TextView) rootView.findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) rootView.findViewById(R.id.dislikeCountView);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        ageImage = (ImageView) rootView.findViewById(R.id.ageImage);
        titleText = (TextView) rootView.findViewById(R.id.titleText);
        ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);

        CommentAdapter adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kym71**", "10분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","0"));
        adapter.addItem(new CommentItem("kym71**", "10분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","0"));

        listView.setAdapter(adapter);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeState) {
                    decrLikeCount();
                } else {
                    incrLikeCount();
                }
                likeState = !likeState;

                if(dislikeState==true) {
                    decrDislikeCount();
                    dislikeState = !dislikeState;
                }
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dislikeState) {
                    decrDislikeCount();
                } else {
                    incrDislikeCount();
                }
                dislikeState = !dislikeState;

                if(likeState==true) {
                    decrLikeCount();
                    likeState = !likeState;
                }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentWriteActivity();
            }
        });

        return rootView;
    }

    public void incrLikeCount() {

        int likeCount = Integer.parseInt(likeCountView.getText().toString());

        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
    }

    public void decrLikeCount() {
        int likeCount = Integer.parseInt(likeCountView.getText().toString());

        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    public void incrDislikeCount() {

        int dislikeCount = Integer.parseInt(dislikeCountView.getText().toString());

        dislikeCount += 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDislikeCount() {

        int dislikeCount = Integer.parseInt(dislikeCountView.getText().toString());

        dislikeCount -= 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.thumbs_down_selector);
    }


    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentItemView view = new CommentItemView(getContext());

            CommentItem item = items.get(position);
            view.setId(item.getId());
            view.setTime(item.getTime());
            view.setComment(item.getComment());
            view.setRecommend(item.getRecommend());

            return view;
        }
    }

    public void showCommentWriteActivity() {
        float rating = ratingBar.getRating();
        String title = titleText.getText().toString();

        Intent intent = new Intent(getContext(), CommentWriteActivity.class);
        intent.putExtra("rating", rating);
        intent.putExtra("title",title);

        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101) {
            if(intent != null) {
                String contents = intent.getStringExtra("contents");
            }
        }
    }
}
