package edu.android.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        TextView link;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent browser_intent = getIntent();
        Bundle bundle = browser_intent.getExtras();

        String URL = bundle.getString("link");
        System.out.println(URL);
        link = (TextView) findViewById(R.id.textView);
        link.setText(URL);

        


    }
}