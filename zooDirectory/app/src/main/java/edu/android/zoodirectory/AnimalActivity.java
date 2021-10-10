package edu.android.zoodirectory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnimalActivity extends AppCompatActivity {

    private TextView aName;
    private TextView aDescription;
    private ImageView aImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        aName = findViewById(R.id.animalName);
        aDescription = findViewById(R.id.animalDescription);
        aImage = findViewById(R.id.animalImage);

        Intent aIntent = getIntent();
        Bundle bundle = aIntent.getExtras();
        String name = bundle.get("animal").toString();
        String description = bundle.get("description").toString();
        Integer image = (Integer) bundle.get("image");

        aName.setText(name);
        aDescription.setText(description);
        aImage.setImageResource(image);

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
