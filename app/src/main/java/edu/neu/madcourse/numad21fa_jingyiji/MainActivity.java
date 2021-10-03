package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void aboutToast(View view) {
        Context about = getApplicationContext();
        CharSequence text = "Jingyi Ji: ji.jing@northeastern.edu";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(about, text, duration);
        toast.show();
    }

    public void linkCollector(View view) {
        Intent intent = new Intent(this, LinkCollector.class);
        startActivity(intent);
    }

    public void Clicky(View view) {
        Intent intent = new Intent(this, ClickyClicky.class);
        startActivity(intent);
    }
}