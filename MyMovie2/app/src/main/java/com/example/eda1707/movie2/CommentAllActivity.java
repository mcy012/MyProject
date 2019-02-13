package com.example.eda1707.movie2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.eda1707.movie2.data.CommentList;
import com.example.eda1707.movie2.data.MovieInfo;
import com.example.eda1707.movie2.data.ResponseInfo;
import com.google.gson.Gson;

import java.util.List;

public class CommentAllActivity extends AppCompatActivity {
    TextView titleAll, totalCount;
    ImageView ageAll, commentWrite;
    RatingBar ratingAll;
    ListView commentAllListView;

    private MovieInfo movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_all);

        titleAll = (TextView) findViewById(R.id.titleAll);
        ratingAll = (RatingBar) findViewById(R.id.ratingAll);
        ageAll = (ImageView) findViewById(R.id.ageAll);
        commentAllListView = (ListView) findViewById(R.id.commentAllListView);
        totalCount = (TextView) findViewById(R.id.totalCount);
        commentWrite = (ImageView) findViewById(R.id.commentWrite);

        Intent intent = getIntent();
        processIntent(intent);

        commentWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentActivity();
            }
        });

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        requestCommentAllList();
    }

    public void showCommentActivity() {
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        startActivity(intent);
    }

    public void processIntent(Intent intent) {
        if (intent != null) {
            float rating = intent.getFloatExtra("rating", 0.0f);
            String title = intent.getStringExtra("title");
            Bitmap bitmap = intent.getParcelableExtra("age");
            int total = intent.getIntExtra("commentTotal",0);

            titleAll.setText(title);
            ratingAll.setRating(rating);
            ageAll.setImageBitmap(bitmap);
            totalCount.setText(String.format("(%s명 참여)", String.valueOf(total)));
        }
    }

    public void requestCommentAllList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList";
        url += "?" + "id=" + movieInfo.getId();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponseCommentAll(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void processResponseCommentAll(String response) {
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);

        if (info.code == 200) {
            CommentList commentList = gson.fromJson(response, CommentList.class);

            MovieDetailFragment.CommentAdapter adapter = new MovieDetailFragment.CommentAdapter();

            adapter.addItems(commentList.getResult());

            commentAllListView.setAdapter(adapter);
        }
    }
}
