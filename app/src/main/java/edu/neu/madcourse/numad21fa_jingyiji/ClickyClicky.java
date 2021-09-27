package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClicky extends AppCompatActivity{
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);


        TextView press = findViewById(R.id.pressed);

        Button a = findViewById(R.id.a);
        a.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    press.setText("Pressed: -");
                    return true;
                }
                press.setText("Pressed: A");
                return true;
            }
        });

        Button b = findViewById(R.id.b);
        b.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    press.setText("Pressed: -");
                    return true;
                }
                press.setText("Pressed: B");
                return true;
            }
        });

        Button c = findViewById(R.id.c);
        c.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    press.setText("Pressed: -");
                    return true;
                }
                press.setText("Pressed: C");
                return true;
            }
        });

        Button d = findViewById(R.id.d);
        d.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    press.setText("Pressed: -");
                    return true;
                }
                press.setText("Pressed: D");
                return true;
            }
        });

        Button e = findViewById(R.id.e);
        e.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    press.setText("Pressed: -");
                    return true;
                }
                press.setText("Pressed: E");
                return true;
            }
        });
        Button f = findViewById(R.id.f);
        f.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    press.setText("Pressed: -");
                    return true;
                }
                press.setText("Pressed: F");
                return true;
            }
        });

    }

}