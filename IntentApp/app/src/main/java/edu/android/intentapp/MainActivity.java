package edu.android.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button call_button;
    Button browser_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call_button = (Button) findViewById(R.id.call_button);
        browser_button = (Button) findViewById(R.id.browser_button);

        call_button.setOnClickListener(view -> {
            String number = "tel:949-123-4444";
            Intent call_Activity = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
            startActivity(call_Activity);
        });

        browser_button.setOnClickListener(view -> {
            String link = "http://www.amazon.com";
            Intent open_browser_activity = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
            Bundle bundle = new Bundle();
            bundle.putString("link",link);
            open_browser_activity.putExtras(bundle);

            Intent chooser = Intent.createChooser(open_browser_activity,"Open");
            try {
                startActivity(chooser);
            }
            catch(ActivityNotFoundException e) {
                e.printStackTrace();
            }

        });

    }
}