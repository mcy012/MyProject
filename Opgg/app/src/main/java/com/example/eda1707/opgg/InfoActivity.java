package com.example.eda1707.opgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView nickName;
    TextView tierRank;
    TextView tierInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        nickName = (TextView) findViewById(R.id.nickName);
        tierRank = (TextView) findViewById(R.id.tierRank);
        tierInfo = (TextView) findViewById(R.id.tierInfo);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ScoreAdapter scoreAdapter = new ScoreAdapter(getApplicationContext());

        recyclerView.setAdapter(scoreAdapter);

        Intent intent = getIntent();
        proccessIntent(intent);
    }

    public void proccessIntent(Intent intent) {
        if(intent != null) {
            String nick = intent.getStringExtra("nickName");
            String tRank = intent.getStringExtra("tierRank");
            String tInfo = intent.getStringExtra("tierInfo");

            if(nick.length()!=0) {
                nickName.setText(nick);
                tierRank.setText(tRank);
                tierInfo.setText(tInfo);
            } else {
                nickName.setText("검색결과가 없습니다.");
                tierRank.setText("닉네임을 확인해주세요");
            }
        }
    }
}
