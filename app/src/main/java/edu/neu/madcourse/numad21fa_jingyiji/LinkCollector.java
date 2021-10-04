package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {
    private ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;
    private RecyclerView.LayoutManager layoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        itemList = new ArrayList<>();

        initialItemData(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                addItem(pos);
            }
        });

        createRecyclerView();

    }

    private void createRecyclerView() {


        layoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        viewAdapter = new ViewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                itemList.get(position).onItemClick(position);
                viewAdapter.notifyItemChanged(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get(position).getItemLink()));
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                } else {
                    String[] pre = new String[]{"https://", "http://", "https://www.", "http://www."};
                    int i = 0;
                    while(browserIntent.resolveActivity(getPackageManager()) == null && i < 4) {
                        Log.d("jj", pre[i] + itemList.get(position).getItemLink());
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pre[i] + itemList.get(position).getItemLink()));
                        i++;
                    }
                    if (browserIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(browserIntent);
                    }
                }
            }
        };
        viewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(viewAdapter);
        recyclerView.setLayoutManager(layoutManger);


    }

    //rotation

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String name = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String link = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");

                    ItemCard itemCard = new ItemCard(link, name);

                    itemList.add(itemCard);
                }
            }
        }

    }
    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {

            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getItemName());
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemLink());

        }
        super.onSaveInstanceState(outState);

    }

    private int pos;

    ActivityResultLauncher<Intent> inputResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();
                        itemList.add(pos, new ItemCard(data.getStringExtra("link"), data.getStringExtra("name")));
                        viewAdapter.notifyItemInserted(pos);

                        Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Successful Added.",
                                Snackbar.LENGTH_LONG)
                                .show();

                    }
                }
            });

    private void addItem(int pos) {
        this.pos = pos;
        Intent intent = new Intent(this, Input.class);
        inputResultLauncher.launch(intent);
    }


}