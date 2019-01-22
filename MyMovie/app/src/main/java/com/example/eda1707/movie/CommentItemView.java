package com.example.eda1707.movie;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentItemView extends LinearLayout {
    TextView textView;
    TextView textView3;
    TextView textView4;
    TextView textView6;
    RatingBar ratingBar;
    Button button;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view, this, true);

        textView = (TextView) findViewById(R.id.textView);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView6= (TextView) findViewById(R.id.textView6);
    }

    public void setId(String id) {
        textView.setText(id);
    }

    public void setTime(String time) {
        textView3.setText(time);
    }

    public void setComment(String comment) {
        textView4.setText(comment);
    }

    public void setRecommend(String recommend) {
        textView6.setText(recommend);
    }



}
