package com.example.shailu.flickflop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private String TAG =MainActivity.class.getSimpleName();
    private GridView gridView;
    private ArrayList<ImageItem> images;
    private GridViewAdapter<ImageItem> gridAdapter;
    private ProgressDialog pDialog;
    private  static String url = "https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=0f56f207cc4c0e81efae7877c622d9cc&photoset_id=72157669702514161&per_page=20&format=json&nojsoncallback=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        pDialog = new ProgressDialog(this);
        images = new ArrayList<ImageItem>();
        gridAdapter = new GridViewAdapter<>(this, R.layout.grid_item_layout,images);
        gridView.setAdapter(gridAdapter);
        fetchImages();
    }

    private void fetchImages() {

        pDialog.setMessage("Loading photos...");
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(url,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        images.clear();
                        try {

                            JSONObject photos = response.getJSONObject("photoset");
                            JSONArray jArray =photos.getJSONArray("photo");
                            String tmp = null;

                            for (int i = 0; i < jArray.length(); i++) {

                                JSONObject jObject = jArray.getJSONObject(i);

                                ImageItem book = new ImageItem();
                                book.setOwnerName(photos.getString("ownername"));
                                book.setTitle(jObject.getString("title"));
                                tmp = "http://farm" +jObject.getString("farm")  + ".staticflickr.com/" + jObject.getString("server") + "/" + jObject.getString("id") + "_" + jObject.getString("secret");// +".jpg";
                                tmp += "_t";
                                tmp += ".jpg";
                                Log.e(TAG,"imageUrl"+tmp);
                                book.setImageUrl(tmp);
                                images.add(book);
                            }

                            gridAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(req);
    }


}