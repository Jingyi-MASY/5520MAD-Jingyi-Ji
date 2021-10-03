package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {
    protected static ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    protected static ViewAdapter viewAdapter;
    private RecyclerView.LayoutManager layoutManger;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        itemList = new ArrayList<>();

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

    //TODO: When the user taps a link in the list, launch the URL in a web browser (use an intent, not android:autolink).

    private void createRecyclerView() {


        layoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        viewAdapter = new ViewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);

                viewAdapter.notifyItemChanged(position);
            }
        };
        viewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(viewAdapter);
        recyclerView.setLayoutManager(layoutManger);


    }

    //TODO: rotation

    // Handling Orientation Changes on Android
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//
//
//        int size = itemList == null ? 0 : itemList.size();
//        outState.putInt(NUMBER_OF_ITEMS, size);
//
//        // Need to generate unique key for each item
//        // This is only a possible way to do, please find your own way to generate the key
//        for (int i = 0; i < size; i++) {
//            // put image information id into instance
//            outState.putInt(KEY_OF_INSTANCE + i + "0", itemList.get(i).getImageSource());
//            // put itemName information into instance
//            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemName());
//            // put itemDesc information into instance
//            outState.putString(KEY_OF_INSTANCE + i + "2", itemList.get(i).getItemDesc());
//            // put isChecked information into instance
//            outState.putBoolean(KEY_OF_INSTANCE + i + "3", itemList.get(i).getStatus());
//        }
//        super.onSaveInstanceState(outState);
//
//    }




    private void addItem(int pos) {
        Intent intent = new Intent(this, Input.class);
        startActivity(intent);
        intent.putExtra("pos", pos);
    }


}