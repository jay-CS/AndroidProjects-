package edu.android.zoodirectory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.animalViewClick{

    private List<String> animals = new ArrayList<>();
    private List<Integer> animal_images = new ArrayList<>();
    private List<String> animal_description = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        animals.add("Alligator");
        animals.add("Sloth");
        animals.add("Zebra");
        animals.add("Giraffe");
        animals.add("Rhino");
        animal_images.add(R.drawable.alligator);
        animal_images.add(R.drawable.sloth);
        animal_images.add( R.drawable.zebra);
        animal_images.add( R.drawable.giraffe);
        animal_images.add( R.drawable.rhino);
        animal_description.add("The alligator is native only to China, Mexico, and the United States. " +
                "Gators in the United States were hunted almost to extinction, but they are now thriving. " +
                "Many states have even turned these big, scaly monsters into tourist attractions.");
        animal_description.add("“The Sloth is the world’s slowest moving mammal.” Sloths are animals that live " +
                "in the treetops of Central and South American rain forests. They spend their days foraging and " +
                "eating leaves, buds and twigs. These slow-moving mammals sleep 15 to 20 hours and only move as " +
                "far as about 40 yards each day.");
        animal_description.add("The Zebra is a large species of equine that is natively found roaming the " +
                "grassy plains of sub-Saharan Africa. They are the largest and most distinctive wild horses " +
                "with bodies that are patterned with white and black stripes, the exact placement of which is " +
                "unique to each individual. ");
        animal_description.add("The Giraffe is a long-necked, hoofed mammal that is natively found grazing in the " +
                "open woodlands of sub-Saharan Africa. The Giraffe is the tallest living animal on land and despite " +
                "its height is mostly closely related to the much smaller and solitary Okapi, that is found elusively" +
                " dwelling in the dense tropical forests. There are nine recognised sub-species of Giraffe that are " +
                "found in differing geographic locations and vary somewhat in the colour and pattern of their spot-like " +
                "markings. ");
        animal_description.add("The rhinoceros is an animal that was once found from Southeast Asia across Africa. Today, " +
                "three rhino species are listed as “Critically Endangered” and clinging to small pockets of habitat." +
                "With its distinctive horn and massive size, the rhino is one of the most unique animals on Earth. However, " +
                "heavy poaching for its horn threatens several rhino species today.");


        // use a linear layout manager
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(animals,animal_images, this::onItemClick);
        recyclerView.setAdapter(mAdapter);
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
                //Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent information_intent = new Intent(this,zoo_information.class);
                startActivity(information_intent);
                return true;

            case R.id.uninstall_item:
                //Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent uninstall_intent = new Intent(Intent.ACTION_DELETE);
                uninstall_intent.setData(Uri.parse("package:edu.android.zoodirectory"));
                startActivity(uninstall_intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent animal_intent = new Intent(this, AnimalActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        if (position == animals.size() - 1) {
            builder.setTitle("FRIGHTENING ANIMAL ALERT!");
            builder.setMessage("ARE YOU SURE YOU WANT TO SEE THIS ANIMAL, ITS FRIGHTENING!!!");
            builder.setCancelable(false);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Bundle bundle = new Bundle();
                    bundle.putString("animal", animals.get(position));
                    bundle.putString("description", animal_description.get(position));
                    bundle.putInt("image", animal_images.get(position));
                    animal_intent.putExtras(bundle);
                    startActivity(animal_intent);

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else {

            Bundle bundle = new Bundle();
            bundle.putString("animal", animals.get(position));
            bundle.putString("description", animal_description.get(position));
            bundle.putInt("image", animal_images.get(position));
            animal_intent.putExtras(bundle);
            startActivity(animal_intent);
        }
    }


}