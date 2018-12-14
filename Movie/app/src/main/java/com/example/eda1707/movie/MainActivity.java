package com.example.eda1707.movie;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;

    InputMethodManager mInputMethodManager;

    ProgressDialog dialog;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.editText)
    EditText editText;

    int movieTotal;

    int startNumber;
    private final static int DISPLAY_NUMBER = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        movieAdapter = new MovieAdapter(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieAdapter.ViewHolder holder, View view, int position) {
                onClickMovieLink(position);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    if(movieTotal > movieAdapter.items.size())
                        sendRequest();
                }
            }
        });

        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    @OnClick(R.id.button)
    public void onClick(View v) {

        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);        //검색버튼 누르면 키 입력창 내려가기기
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        if(editText.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "검색어를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else {
            movieAdapter.clearItems();
            showProgressDialog();
            startNumber=1;
            sendRequest();
            recyclerView.setVisibility(View.VISIBLE);
            return;
        }
    }

    /**
     * 영화 목록에서 클릭한 영화 링크로 이동하는 메소드
     *
     * @param position 클릭한 영화 목록 포지션 값
     */
    private void onClickMovieLink(int position) {
        try {
            MovieInfo item = movieAdapter.getItem(position);

            String url = item.getLink();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();                    //크롬 커스텀 탭

            builder.setToolbarColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null)); //색깔
            builder.setExitAnimations(MainActivity.this, R.anim.slide_in_left, R.anim.slide_out_right); //애니메이션
            builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.back_arrow)); //아이콘?

            customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
        } catch (NullPointerException e) {
            Log.e(this.getClass().getName(), "영화 링크가 존재하지 않습니다.", e);

            Toast.makeText(this, "영화 링크가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "영화 링크 이동 중 알 수 없는 오류가 발생하였습니다.", e);

            Toast.makeText(this, "영화 링크 이동 중 알 수 없는 오류가 발생하였습니다. 다시 시도해 주시기 바랍니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("잠시만요..");
        dialog.show();
    }

    private void cancelProgressDialog() {
        dialog.dismiss();
    }

    public void sendRequest() {

        String url = "https://openapi.naver.com/v1/search/movie.json?query=" + editText.getText().toString() + "&display=" + DISPLAY_NUMBER + "&start=" + startNumber;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                        cancelProgressDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                        cancelProgressDialog();
                    }
                }
        ) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                Map params = new HashMap();
                params.put("X-Naver-Client-Id", "qoVBFXrOuojTZv402K_f");
                params.put("X-Naver-Client-Secret", "eIsMSoxWQ4");

                return params;
            }
        };
        request.setShouldCache(false);          //이전결과있어도 새로 요청
        AppHelper.requestQueue.add(request);
    }

    public void processResponse(String response) {
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class);

        if(movieList != null && movieList.items.size() != 0) {
            movieAdapter.addItems(movieList.items);
            startNumber += movieList.items.size();
            movieTotal = movieList.total;
        } else {
            Toast.makeText(this, "\'"+ editText.getText().toString() + "\' 검색 결과는 없습니다..", Toast.LENGTH_SHORT).show();
            movieTotal = 0;
        }
    }

    public void println(String data) {
        Log.i(getClass().getName().toString(), data + "\n");
    }
}


