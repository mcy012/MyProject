package com.example.eda1707.opgg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eda1707.opgg.dto.MyInfoDto;
import com.example.eda1707.opgg.dto.MyScoreDto;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder>{
    Context context;

    List<MyInfoDto> items = new ArrayList<>();

    public ScoreAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.my_score_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyInfoDto item = items.get(i);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView gameType;
        TextView gameResult;
        TextView kda;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameType = (TextView) itemView.findViewById(R.id.gameType);
            gameResult = (TextView) itemView.findViewById(R.id.gameResult);
            kda = (TextView) itemView.findViewById(R.id.kda);
        }

        public void setItem(MyInfoDto item) {
            gameType.setText(item.getMyScoreDtoList().toString());
        }
    }
}
