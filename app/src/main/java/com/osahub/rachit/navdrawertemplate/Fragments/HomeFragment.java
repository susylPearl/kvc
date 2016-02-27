package com.osahub.rachit.navdrawertemplate.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.osahub.rachit.navdrawertemplate.Classes.AppController;
import com.osahub.rachit.navdrawertemplate.Classes.JsonObjectClass;
import com.osahub.rachit.navdrawertemplate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

public class HomeFragment extends android.support.v4.app.Fragment {

    public HomeFragment(){

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity)getActivity()).setActionBarTitle("KVC Achievements");

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Toast.makeText(getActivity(), "Data Loading Started..", Toast.LENGTH_SHORT).show();

        final ProgressDialog pDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Loading...");
        pDialog.show();


//        final List<NavigationItem> navigationItems = getMenu();
//
        // Tag used to cancel the reques
        String tag_json_obj = "json_obj_req";
        final String TAG = AppController.class
                .getSimpleName();

//        String url = "http://api.androidhive.info/volley/person_object.json";
        String url = "http://demo.peacenepal.com/kvc/college/api/contents";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                            if (response != null){
                                try {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(response));

                                    //Fetching json array node
                                    JSONArray about_us = jsonObject.getJSONArray("contents");

                                    //looping through all about_us
                                    for (int i=0; i<about_us.length(); i++) {

                                        int id = Integer.parseInt(about_us.getJSONObject(i).getString("id"));
                                        if (id == 56 ) {
                                            JSONObject a = about_us.getJSONObject(i);

                                            Iterator<String> iterator = a.keys();
                                            while (iterator.hasNext()){
                                                String currentKey = iterator.next();
                                                Log.d(TAG, currentKey);
                                            }
                                            String content = a.getString("content_description");
                                            String intro = a.getString("content_heading");

                                            TextView textView = (TextView) rootView.findViewById(R.id.intro);
                                            TextView textView1 = (TextView) rootView.findViewById(R.id.content);

                                            SpannableString intro1 = new SpannableString(intro);
                                            intro1.setSpan(new UnderlineSpan(), 0, intro1.length(), 0);

                                            textView.setText(intro1);
                                            textView1.setText(Html.fromHtml(content));
                                            pDialog.hide();
                                            Toast.makeText(getActivity(), "Data loading Completed...", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }


                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
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

        return rootView;
    }
}