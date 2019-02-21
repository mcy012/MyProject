package com.example.eda1707.opgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

        Intent intent = getIntent();
        proccessIntent(intent);
    }

    public void proccessIntent(Intent intent) {
        if(intent != null) {
            String nick = intent.getStringExtra("nickName");
            nickName.setText(nick);
        }
    }
}
