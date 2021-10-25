package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class Web extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";

    private EditText mURLEditText;
    private TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mURLEditText = (EditText)findViewById(R.id.URL_editText);
        mTitleTextView = (TextView)findViewById(R.id.result_textview);

    }

    public void execute(View view){
        ProgressBar bar = findViewById(R.id.progress);

        //Progress bar
        bar.setVisibility(View.VISIBLE);
        PingWebServiceTask task = new PingWebServiceTask();
        task.execute(mURLEditText.getText().toString());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bar.setVisibility(View.INVISIBLE);
            }
        }, 1000);
    }



    // docs of Metro https://metmuseum.github.io/
    private class PingWebServiceTask  extends AsyncTask<String, Integer, String[]> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Making progress...");

        }

        @Override
        protected String[] doInBackground(String... params) {


            String[] results = new String[3];
            URL url = null;
            try {
                //https://github.com/public-apis/public-apis
                url = new URL("https://collectionapi.metmuseum.org/public/collection/v1/objects/437133");
//                url = new URL(params[0]);


                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);


                conn.connect();
                Log.d(TAG, "connected successfully");

                // Read response.
                InputStream inputStream = conn.getInputStream();

                final String resp = convertStreamToString(inputStream);

//                JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
                JSONObject jObject = new JSONObject(resp);
                Log.d(TAG, "convert successfully");
                String jTitle = jObject.getString("title");
                String jBody = jObject.getString("primaryImageSmall");
                String jArtist = jObject.getString("artistDisplayName");

                results[0] = jTitle;
                results[1] = jBody;
                results[2] = jArtist;
                return results;

            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG,"JSONException");
                e.printStackTrace();
            }
            results[0] = "Something went wrong";
            return results;
        }


        @Override
        protected void onPostExecute(String... s) {
            super.onPostExecute(s);
            TextView result_view = (TextView)findViewById(R.id.result_textview);
            TextView result_name = (TextView)findViewById(R.id.result_name);
            WebView web = (WebView) findViewById(R.id.web_view);

            if(!s[0].equals("Something went wrong")) {
                result_view.setText(s[0]);
                result_name.setText(s[2]);
                try {
                    Log.i(TAG, s[1]);


                    //Was not sure what the input is: can use objectWikidata_URL to get wiki page instead.
//                    web.loadUrl(String.valueOf(new URL(s[1])));
                    web.loadUrl(String.valueOf(new URL("https://images.metmuseum.org/CRDImages/ep/web-large/DT1567.jpg")));

                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                }
            }

        }
    }

    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }
}