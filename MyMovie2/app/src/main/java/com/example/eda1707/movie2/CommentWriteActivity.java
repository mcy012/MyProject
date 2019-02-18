package com.example.eda1707.movie2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class CommentWriteActivity extends AppCompatActivity {
    RatingBar ratingBar;
    EditText contentsInput;
    TextView titleText;
    ImageView ageImage;

    int movieId;

    MovieDetailFragment movieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        contentsInput = (EditText) findViewById(R.id.contentsInput);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        titleText = (TextView) findViewById(R.id.titleText);

        ageImage = (ImageView) findViewById(R.id.ageImage);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    public void processIntent(Intent intent) {
        if(intent != null) {
            float rating = intent.getFloatExtra("rating", 0.0f);
            String title = intent.getStringExtra("title");
            Bitmap bitmap = intent.getParcelableExtra("age");
            movieId = intent.getIntExtra("movieId",0);

            ratingBar.setRating(rating);
            titleText.setText(title);
            ageImage.setImageBitmap(bitmap);

        }
    }

    public void returnToMain() {
        final String contents = contentsInput.getText().toString();

        if(contents.length() == 0) {
            Toast.makeText(getApplicationContext(), "한줄평을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("contents", contents);

        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/createComment";
        url += "?" + "id=" +movieId;

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("writer", "qqqqq");
                        params.put("contents", contents);
                        params.put("rating", String.valueOf(ratingBar.getRating()));
                        return params;
                    }
                };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

}
