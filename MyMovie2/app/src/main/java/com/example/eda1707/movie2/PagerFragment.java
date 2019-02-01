package com.example.eda1707.movie2;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.eda1707.movie2.data.MovieInfo;
import com.example.eda1707.movie2.data.MovieList;

import java.util.ArrayList;

public class PagerFragment extends Fragment{

    private MovieList movieList;

    public static  PagerFragment newInstance(MovieList movieList) {
        PagerFragment fragmentFirst = new PagerFragment();
        Bundle args = new Bundle();
        args.putParcelable("movieList", movieList);

        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.movieList = getArguments().getParcelable("movieList");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_fragment, container,false);

        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(10);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getChildFragmentManager());
        /*MovieListFragment fragment1 = new MovieListFragment();
        adapter.addItem(fragment1);

        MovieListFragment fragment2 = new MovieListFragment();
        adapter.addItem(fragment2);

        MovieListFragment fragment3 = new MovieListFragment();
        adapter.addItem(fragment3);

        MovieListFragment fragment4 = new MovieListFragment();
        adapter.addItem(fragment4);

        MovieListFragment fragment5 = new MovieListFragment();
        adapter.addItem(fragment5);*/

        for(int index=0; index < movieList.result.size(); index++) {
            adapter.addItem(MovieListFragment.newInstance(movieList.result.get(index)));
        }

        pager.setAdapter(adapter);

        return rootView;
    }

    public class MoviePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int i) {
            return items.get(i);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

}
