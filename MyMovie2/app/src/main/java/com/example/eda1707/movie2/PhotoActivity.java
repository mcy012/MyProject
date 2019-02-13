package com.example.eda1707.movie2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.eda1707.movie2.data.PhotoInfoDto;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {
    PhotoView photoView;
    String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photoView = (PhotoView) findViewById(R.id.photoView);

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent != null) {
            imageUrl = intent.getStringExtra("imgUrl");
            Glide.with(getApplicationContext()).load(imageUrl).into(photoView);
        }
    }
}
