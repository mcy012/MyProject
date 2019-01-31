package com.example.eda1707.movie2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eda1707.movie2.data.MovieInfo;

import java.util.List;

public class MovieListFragment extends Fragment {
    MovieDetailFragment detailFragment;
    ImageView poster;
    TextView movieTitle;
    TextView movieData;

    private MovieInfo movieInfo;

    public static  MovieListFragment newInstance(MovieInfo movieInfo) {
        MovieListFragment fragmentFirst = new MovieListFragment();
        Bundle args = new Bundle();
        args.putParcelable("movieInfo", movieInfo);

        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.movieInfo = getArguments().getParcelable("movieInfo");

        Log.i("ganzi", ">>>>>>>>>>>> " + movieInfo.toString());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("ganzi", ">>>>>>>>>>>> onCreateView");

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.movie_list_fragment, container, false);

        detailFragment = new MovieDetailFragment();

        poster = (ImageView) rootView.findViewById(R.id.poster);
        movieTitle = (TextView) rootView.findViewById(R.id.movieTitle);
        movieData = (TextView) rootView.findViewById(R.id.movieData);



        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
            }
        });

        return rootView;
    }
}
