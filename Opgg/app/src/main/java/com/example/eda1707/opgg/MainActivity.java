package com.example.eda1707.opgg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eda1707.opgg.dto.MyInfoDto;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    MyInfoDto myInfoDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpggAsyncTask opggAsyncTask = new OpggAsyncTask();
                opggAsyncTask.execute();
            }
        });
    }

    class OpggAsyncTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩 중");

            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                final Intent intent = new Intent(getApplicationContext(), InfoActivity.class);

                OPGG opgg = new OPGG();
                myInfoDto = opgg.getMyInfo(editText.getText().toString());
                if (myInfoDto != null) {
                    Log.i("ganzi", ">>>>>>>>"+myInfoDto.getTierRank());
                    intent.putExtra("nickName", myInfoDto.getNickName());
                    intent.putExtra("tierRank", myInfoDto.getTierRank());
                    intent.putExtra("tierInfo", myInfoDto.getTierInfo());
                    startActivity(intent);
                } else {
                    Log.i("ganzi", "zzzzz");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            asyncDialog.dismiss();
        }
    }

}
