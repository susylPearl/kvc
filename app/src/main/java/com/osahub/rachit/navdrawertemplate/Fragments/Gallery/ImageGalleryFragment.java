package com.osahub.rachit.navdrawertemplate.Fragments.Gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Activities.NavActivity;
import com.osahub.rachit.navdrawertemplate.Adapters.ImageGalleryAdapter;
import com.osahub.rachit.navdrawertemplate.Classes.AppController;
import com.osahub.rachit.navdrawertemplate.Classes.ItemOffsetDecoration;
import com.osahub.rachit.navdrawertemplate.Classes.NavigationItem;
import com.osahub.rachit.navdrawertemplate.Classes.PhotoAlbum;
import com.osahub.rachit.navdrawertemplate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sushil on 1/25/16.
 */
public class ImageGalleryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView mRecycleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView = null;
    private List<PhotoAlbum> albums;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        albums = new ArrayList<PhotoAlbum>();


        rootView = inflater.inflate(R.layout.fragment_academics_program, container, false);


        mRecycleView = (RecyclerView) rootView.findViewById(R.id.recyclerview_grid);
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        mRecycleView.addItemDecoration(itemDecoration);



        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                fetchData();
            }
        });





        return rootView;
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    public void fetchData(){


        swipeRefreshLayout.setRefreshing(false);
        // Tag used to cancel the reques
        String tag_json_obj = "json_obj_req";
        final String TAG = AppController.class
                .getSimpleName();

//        String url = "http://api.androidhive.info/volley/person_object.json";
        String url = "http://demo.peacenepal.com/kvc/college/api/gallery";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        albums.clear();

                        if (response != null){
                            try {


                                JSONObject jsonObject = new JSONObject(String.valueOf(response));

                                JSONArray ja = jsonObject.getJSONArray("albums");
                                Log.d(TAG, String.valueOf(ja));

                                for (int i=0; i<ja.length(); i++){
                                    JSONObject jobj = ja.getJSONObject(i);
                                    int id = Integer.parseInt(jobj.getString("id"));
                                    String title = jobj.getString("album_title");
//                                    String created = jobj.getString("created_at");
//                                    String updated = jobj.getString("updated_at");
                                    String description = jobj.getString("album_description");
                                    String url = jobj.getString("album_attachment");

                                    List<String> imageUrls = new ArrayList<String>();
                                    JSONArray jsonArray = jobj.getJSONArray("galleries");
                                    for (int j=0; j<jsonArray.length(); j++){

                                        JSONObject object = jsonArray.getJSONObject(j);
                                        String Url = object.getString("media_attachment");
                                        imageUrls.add(Url);

                                    }

                                    String[] stringArrayUrl = imageUrls.toArray(new String[imageUrls.size()]);

                                    albums.add(new PhotoAlbum(url, title, id, null, null, description, stringArrayUrl));

                                }


                                ImageGalleryAdapter adapter = new ImageGalleryAdapter(getActivity(), albums);
                                mRecycleView.setAdapter(adapter);





//                                    pDialog.hide();
//                                    Toast.makeText(getActivity(), "Data Loading completed..", Toast.LENGTH_SHORT).show();


                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        swipeRefreshLayout.setRefreshing(false);

                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
//                pDialog.hide();
                swipeRefreshLayout.setRefreshing(false);
            }
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
//                    headerValue = response.headers.get("Last-Modified");
//                    if (headerValue != null) {
//                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
//                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(JSONObject response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };



// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);



    }
}
