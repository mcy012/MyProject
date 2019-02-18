package com.example.eda1707.movie2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.example.eda1707.movie2.data.CommentInfo;
import com.example.eda1707.movie2.data.CommentList;
import com.example.eda1707.movie2.data.MovieInfo;
import com.example.eda1707.movie2.data.ResponseInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CommentAllActivity extends AppCompatActivity {
    TextView titleAll, totalCount;
    ImageView ageAll, commentWrite;
    RatingBar ratingAll;
    ListView commentAllListView;

    int movieId;

    float rating;
    String title;

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

    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentInfo> items = new ArrayList<CommentInfo>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentInfo item) {
            items.add(item);
        }

        public void addItems(ArrayList<CommentInfo> items) {
            this.items.addAll(items);
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

            CommentInfo item = items.get(position);
            view.setWriter(item.getWriter());
            view.setTime(item.getTime());
            view.setComment(item.getContents());
            view.setRecommend(item.getRecommend());
            view.setRating(item.getRating());

            return view;
        }
    }

    public void processIntent(Intent intent) {
        if (intent != null) {
            rating = intent.getFloatExtra("rating", 0.0f);
            title = intent.getStringExtra("title");
            Bitmap bitmap = intent.getParcelableExtra("age");
            int total = intent.getIntExtra("commentTotal",0);
            movieId = intent.getIntExtra("movieId",0);

            titleAll.setText(title);
            ratingAll.setRating(rating);
            ageAll.setImageBitmap(bitmap);
            totalCount.setText(String.format("(%s명 참여)", String.valueOf(total)));
        }
    }

    public void showCommentActivity() {

        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        intent.putExtra("rating", rating);
        intent.putExtra("title", title);
        intent.putExtra("movieId", movieId);

        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 101) {
            if (intent != null) {
                requestCommentAllList();
            }
        }
    }

    public void requestCommentAllList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList";
        url += "?" + "id=" + movieId;

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

            CommentAdapter adapter = new CommentAdapter();

            adapter.addItems(commentList.getResult());

            commentAllListView.setAdapter(adapter);

            //Log.i("ganzi", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + movieInfo.toString());
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
