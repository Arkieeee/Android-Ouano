package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "This is verbose log");
        Log.d(TAG, "This is debug log");
        Log.i(TAG, "This is an info log");
        Log.w(TAG, "This is a warm log");
        Log.e(TAG, "This is an error log");

        Button button = (Button) findViewById(R.id.button5);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG,"Button 1 Clicked");
                    }
                });
        Button button2 = (Button) findViewById(R.id.button6);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Button 2 Clicked");
            }
        });


    }
}