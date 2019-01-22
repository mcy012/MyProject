package com.example.eda1707.movie;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class CommentWriteActivity extends AppCompatActivity {
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        processIntent(intent);
    }

    public void processIntent(Intent intent) {
        if(intent != null) {
            float rating = intent.getFloatExtra("rating", 0.0f);
            ratingBar.setRating(rating);
        }
    }
}
