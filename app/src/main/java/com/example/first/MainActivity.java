package com.example.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Clickbutton(View v)
    {
        Toast.makeText(getApplicationContext(), "Hello World!", Toast.LENGTH_LONG).show();
    }
}
