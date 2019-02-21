package com.example.eda1707.opgg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eda1707.opgg.dto.MyInfoDto;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("nickName", editText.getText().toString());

                OPGG opgg = new OPGG();
                try {
                    MyInfoDto myInfoDto = opgg.getMyInfo("hideonbush");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

    }
}
