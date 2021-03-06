package com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.osahub.rachit.navdrawertemplate.Adapters.NewsFragmentAdapter;
import com.osahub.rachit.navdrawertemplate.Classes.AppController;
import com.osahub.rachit.navdrawertemplate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by sushil on 1/25/16.
 */
public class NewsFragments extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    Toolbar mToolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView = null;
    private ArrayList<HashMap<String, String>> results = null;
    private LinearLayoutManager layoutManager = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        rootView = inflater.inflate(R.layout.tab_fragment_2, container, false);

         results = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
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



//        Toast.makeText(getActivity(), "Data Loading Started..", Toast.LENGTH_SHORT).show();
//
//        final ProgressDialog pDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
//        pDialog.setIndeterminate(true);
//        pDialog.setMessage("Loading...");
//        pDialog.show();


//        final List<NavigationItem> navigationItems = getMenu();
//
        swipeRefreshLayout.setRefreshing(true);
        // Tag used to cancel the reques
        String tag_json_obj = "json_obj_req";
        final String TAG = AppController.class
                .getSimpleName();

//        String url = "http://api.androidhive.info/volley/person_object.json";
        String url = "http://demo.peacenepal.com/kvc/college/api/news";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        results.clear();
                        if (response != null){
                            try {
                                JSONObject jsonObject = null;
                                try {
                                    JSONArray about_us = null;
                                    jsonObject = new JSONObject(String.valueOf(response));
                                    about_us = jsonObject.getJSONArray("news");

                                    HashMap<String, String> currentMap = null;
                                    for (int i=0; i<about_us.length(); i++) {

                                        currentMap = new HashMap<>();

                                        JSONObject object = about_us.getJSONObject(i);
                                        String title = object.getString("news_heading");
                                        String body = object.getString("news_short_description");
                                        String description = object.getString("news_description");
                                        String date = object.getString("news_date");

                                        currentMap.put("title", title);
                                        currentMap.put("body", body);
                                        currentMap.put("date", date);
                                        currentMap.put("description", description);

                                        NewsFragmentAdapter adapter = new NewsFragmentAdapter(getActivity(), results);

                                        recyclerView.setHasFixedSize(true);
                                        recyclerView.setLayoutManager(layoutManager);
                                        recyclerView.setAdapter(adapter);
                                        recyclerView.setClickable(true);


                                        if (currentMap != null && !currentMap.isEmpty())
                                        {
                                            results.add(currentMap);
                                        }


//                                        pDialog.hide();

                                    }


                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }

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
