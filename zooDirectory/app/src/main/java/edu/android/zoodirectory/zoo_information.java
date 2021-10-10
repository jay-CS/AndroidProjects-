package edu.android.zoodirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class zoo_information extends AppCompatActivity {

    private Button callButton;
    private TextView zooName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_information);

        callButton = (Button) findViewById(R.id.zooNumberButton);
        zooName = (TextView) findViewById(R.id.companyName);

        callButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String number = "tel:888-8888";
                Intent call_intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
                startActivity(call_intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.zoo_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.zoo_information_item:
                Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent information_intent = new Intent(this,zoo_information.class);
                startActivity(information_intent);
                return true;

            case R.id.uninstall_item:
                Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent uninstall_intent = new Intent(Intent.ACTION_DELETE);
                uninstall_intent.setData(Uri.parse("package:edu.android.zoodirectory"));
                startActivity(uninstall_intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}