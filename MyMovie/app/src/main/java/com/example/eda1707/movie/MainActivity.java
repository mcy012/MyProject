package com.example.eda1707.movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView likeCountView, dislikeCountView;
    Button likeButton, dislikeButton;
    ImageView imageView2;
    RatingBar ratingBar;

    boolean likeState = false;
    boolean dislikeState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (Button) findViewById(R.id.likeButton);
        dislikeButton = (Button) findViewById(R.id.dislikeButton);

        likeCountView = (TextView) findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);

        ListView listView = (ListView) findViewById(R.id.listView);

        imageView2 = (ImageView) findViewById(R.id.imageView2);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

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
            CommentItemView view = new CommentItemView(getApplicationContext());

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

       Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
       intent.putExtra("rating", rating);
       startActivityForResult(intent, 101);

    }
}

