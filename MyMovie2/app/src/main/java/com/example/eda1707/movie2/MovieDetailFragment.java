package com.example.eda1707.movie2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.eda1707.movie2.data.CommentInfo;
import com.example.eda1707.movie2.data.CommentList;
import com.example.eda1707.movie2.data.MovieDetailInfo;
import com.example.eda1707.movie2.data.MovieDetailList;
import com.example.eda1707.movie2.data.MovieInfo;
import com.example.eda1707.movie2.data.PhotoInfoDto;
import com.example.eda1707.movie2.data.PhotoType;
import com.example.eda1707.movie2.data.ResponseInfo;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieDetailFragment extends Fragment {

    TextView likeCountView, dislikeCountView, titleText, synopsis, reservation, audience, director, actor, openDay, genreTime;
    Button likeButton, dislikeButton, all, refresh;
    ImageView imageView2, ageImage, detailPoster;
    RatingBar ratingBar;

    ListView listView;

    boolean likeState = false;
    boolean dislikeState = false;

    private MovieInfo movieInfo;

    RecyclerView recyclerView;

    MovieAdapter adapter;

    int countTotal;

    ConstraintLayout errorMessage;

    /**
     * newinstance로 프래그먼트에 movieinfo 값 넘겨주기
     * @param movieInfo
     * @return
     */
    public static MovieDetailFragment newInstance(MovieInfo movieInfo) {
        MovieDetailFragment fragmentFirst = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movieInfo", movieInfo);

        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.movieInfo = getArguments().getParcelable("movieInfo");

        Log.i("ganzi", "************  " + this.movieInfo.toString());

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getContext());
        }

        getActivity().setTitle("영화 상세");

        requestMovieDetailList();
        requestCommentList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.movie_detail_fragment, container, false);

        likeButton = (Button) rootView.findViewById(R.id.likeButton);
        dislikeButton = (Button) rootView.findViewById(R.id.dislikeButton);
        all = (Button) rootView.findViewById(R.id.all);

        likeCountView = (TextView) rootView.findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) rootView.findViewById(R.id.dislikeCountView);

        listView = (ListView) rootView.findViewById(R.id.listView);

        imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        ageImage = (ImageView) rootView.findViewById(R.id.ageImage);
        titleText = (TextView) rootView.findViewById(R.id.titleText);
        ratingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);

        synopsis = (TextView) rootView.findViewById(R.id.synopsis);
        reservation = (TextView) rootView.findViewById(R.id.reservation);
        audience = (TextView) rootView.findViewById(R.id.audience);
        director = (TextView) rootView.findViewById(R.id.director);
        actor = (TextView) rootView.findViewById(R.id.actor);
        openDay = (TextView) rootView.findViewById(R.id.openDay);
        genreTime = (TextView) rootView.findViewById(R.id.genreTime);

        detailPoster = (ImageView) rootView.findViewById(R.id.detailPoster);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        errorMessage = (ConstraintLayout) rootView.findViewById(R.id.errorMessage);

        refresh = (Button) rootView.findViewById(R.id.refresh);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeState) {
                    decrLikeCount();
                } else {
                    incrLikeCount();
                }
                likeState = !likeState;

                if (dislikeState == true) {
                    decrDislikeCount();
                    dislikeState = !dislikeState;
                }
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dislikeState) {
                    decrDislikeCount();
                } else {
                    incrDislikeCount();
                }
                dislikeState = !dislikeState;

                if (likeState == true) {
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

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentAllActivity();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCommentList();
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
            CommentItemView view = new CommentItemView(getContext());

            CommentInfo item = items.get(position);
            view.setWriter(item.getWriter());
            view.setTime(item.getTime());
            view.setComment(item.getContents());
            view.setRecommend(item.getRecommend());
            view.setRating(item.getRating());

            return view;
        }
    }

    public void showCommentWriteActivity() {
        float rating = ratingBar.getRating();
        String title = titleText.getText().toString();
        int movieId = movieInfo.getId();

        Intent intent = new Intent(getContext(), CommentWriteActivity.class);
        intent.putExtra("rating", rating);
        intent.putExtra("title", title);
        intent.putExtra("movieId", movieId);

        startActivity(intent);
    }

    /**
     * 프로그먼트가 resume상태일 때 댓글목록요청 다시하기
     */
    @Override
    public void onResume() {
        super.onResume();

        requestCommentList();
        Log.i("ganzi", ">>>>>>>>>>>>>> 다시 살아났다?");
    }

    /**
     * 한줄평모두보기
     */

    public void showCommentAllActivity() {
        float rating = ratingBar.getRating();
        String title = titleText.getText().toString();
        Bitmap bitmap = ((BitmapDrawable)ageImage.getDrawable()).getBitmap();
        int commentTotal = countTotal;
        int movieId = movieInfo.getId();

        Intent intent = new Intent(getContext(), CommentAllActivity.class);

        intent.putExtra("rating", rating);
        intent.putExtra("title", title);
        intent.putExtra("age", bitmap);
        intent.putExtra("commentTotal", commentTotal);
        intent.putExtra("movieId", movieId);

        startActivity(intent);
    }

    /**
     * 영화상세요청
     */

    public void requestMovieDetailList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovie";
        url += "?" + "id=" + movieInfo.getId();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
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

    public void processResponse(String response) {
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);

        if (info.code == 200) {
            MovieDetailList movieDetailList = gson.fromJson(response, MovieDetailList.class);

            for (int i = 0; i < movieDetailList.getItems().size(); i++) {
                MovieDetailInfo movieDetailInfo = movieDetailList.getItems().get(i);

                String rate = Float.toString(movieDetailInfo.getReservation_rate());
                String grade = Integer.toString(movieDetailInfo.getReservation_grade());

                String like = Integer.toString(movieDetailInfo.getLike());
                String dislike = Integer.toString(movieDetailInfo.getDislike());
                String audienceCount = Integer.toString(movieDetailInfo.getAudience());
                String duration = Integer.toString(movieDetailInfo.getDuration());

                titleText.setText(movieDetailInfo.getTitle());
                Glide.with(getContext()).load(movieDetailInfo.getImage()).into(detailPoster);
                synopsis.setText(movieDetailInfo.getSynopsis());
                likeCountView.setText(like);
                dislikeCountView.setText(dislike);
                reservation.setText(grade + "위 " + rate + "%");
                audience.setText(audienceCount + "명");
                director.setText(movieDetailInfo.getDirector());
                actor.setText(movieDetailInfo.getActor());
                ratingBar.setRating(movieDetailInfo.getUser_rating());
                openDay.setText(movieDetailInfo.getDate() + " 개봉");
                genreTime.setText(movieDetailInfo.getGenre() + " / " + duration + " 분");

                if (movieDetailInfo.getGrade() == 12) {
                    ageImage.setImageResource(R.drawable.ic_12);
                } else if (movieDetailInfo.getGrade() == 15) {
                    ageImage.setImageResource(R.drawable.ic_15);
                } else if (movieDetailInfo.getGrade() == 19) {
                    ageImage.setImageResource(R.drawable.ic_19);
                } else {
                    ageImage.setImageResource(R.drawable.ic_all);
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                /**
                 * MovieAdapter 만들때 사진이랑 비디오 값을 넘겨준다. adapter에서 넘겨준 값 조종
                  */
                adapter = new MovieAdapter(getContext(), movieDetailInfo.getPhotos(), movieDetailInfo.getVideos());
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MovieAdapter.ViewHolder holder, View view, int position) {

                        Log.i("ganzi", adapter.getItem(position).toString());
                        showPhotoActivity(adapter.getItem(position));
                    }
                });
            }
        }
    }

    public void showPhotoActivity(PhotoInfoDto photoInfoDto){

        if (photoInfoDto.getType() == PhotoType.IMAGE) {
            Intent intent = new Intent(getContext(), PhotoActivity.class);
            intent.putExtra("imgUrl", photoInfoDto.getImgUrl());
            startActivity(intent);
        } else {
            String videoUrl = photoInfoDto.getVideoUrl();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            startActivity(intent);
        }
    }

    /**
     * 댓글목록요청
     */
    public void requestCommentList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList";
        url += "?" + "id=" + movieInfo.getId() + "&limit=2";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponseComment(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorResponseComment(error);
                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void processResponseComment(String response) {
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);

        if (info.code == 200) {
            CommentList commentList = gson.fromJson(response, CommentList.class);

            CommentAdapter adapter = new CommentAdapter();

            countTotal = commentList.getTotalCount();

            adapter.addItems(commentList.getResult());

            listView.setAdapter(adapter);
        }
        errorMessage.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    /**
     * 에러처리할라고하는중
     */
    public void errorResponseComment(VolleyError error) {
        errorMessage.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }
}
