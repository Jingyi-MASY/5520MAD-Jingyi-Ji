package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Input extends AppCompatActivity {
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        int pos = getIntent().getIntExtra("pos", 0);

    }

    public void confirm(View view) {
        EditText link = (EditText) findViewById(R.id.input_link);

        EditText name = (EditText) findViewById(R.id.input_name);

        //TODO: Input check, snackbar


        LinkCollector.itemList.add(pos, new ItemCard(link.getText().toString(), name.getText().toString()));
        LinkCollector.viewAdapter.notifyItemInserted(pos);
        this.finish();
    }


}