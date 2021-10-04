package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }
    //TODO: Snap bar; All URL format

    public void confirm(View view) {
        EditText link = (EditText) findViewById(R.id.input_link);

        EditText name = (EditText) findViewById(R.id.input_name);
        Log.d("jj", String.valueOf(URLUtil.isValidUrl(link.getText().toString())));

        if(Patterns.WEB_URL.matcher(link.getText().toString()).matches()) {
            Intent intent = new Intent();
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("link", link.getText().toString());
            setResult(RESULT_OK, intent);
            this.finish();
        }
        Snackbar.make(view, "Invalid link: Please Re-enter.",
                Snackbar.LENGTH_LONG)
                .show();
    }



}