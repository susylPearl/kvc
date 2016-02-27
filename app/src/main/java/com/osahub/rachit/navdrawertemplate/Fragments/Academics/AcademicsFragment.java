package com.osahub.rachit.navdrawertemplate.Fragments.Academics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import com.osahub.rachit.navdrawertemplate.Adapters.AcademicsAdapter;
import com.osahub.rachit.navdrawertemplate.Adapters.PagerAdapter;
import com.osahub.rachit.navdrawertemplate.Classes.AppController;
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
public class AcademicsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

        final List<String> list = new ArrayList<String>();


//
//        final ProgressDialog pDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
//        pDialog.setIndeterminate(true);
//        pDialog.setMessage("Loading...");
//        pDialog.show();



        // Tag used to cancel the reques
        String tag_json_obj = "json_obj_req";
        final String TAG = AppController.class
                .getSimpleName();

//        String url = "http://api.androidhive.info/volley/person_object.json";
        String url = "http://demo.peacenepal.com/kvc/college/api/showMenu";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        list.clear();

                        if (response != null){
                            try {

                                JSONObject jsonObject = new JSONObject(String.valueOf(response));
                                JSONObject jo = jsonObject.getJSONObject("0");

                                JSONArray ja = jo.getJSONArray("Academic Programs  ");
                                Log.d(TAG, String.valueOf(ja));
                                JSONObject jobj = ja.getJSONObject(0);
                                JSONArray jar = jobj.getJSONArray("children");
                                JSONObject jsonObject1 = jar.getJSONObject(0);
                                JSONArray jsonArray = jsonObject1.getJSONArray("HSEB(+2)");
                                JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                                JSONArray jsonArray1 = jsonObject2.getJSONArray("children");
                                JSONObject jsonObject3 = jsonArray1.getJSONObject(0);


                                Iterator<String> iterator = jsonObject3.keys();


                                while (iterator.hasNext()) {
                                    String currentKey = iterator.next();
                                    Log.d(TAG, currentKey);
                                    list.add(currentKey);
                                }


                                String[] keys = list.toArray(new String[list.size()]);
                                TabLayout tabLayout = (TabLayout)rootView.findViewById(R.id.tab_layout);

                                for (int i=0; i<keys.length; i++) {
                                    tabLayout.addTab(tabLayout.newTab().setText(keys[i]));

                                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                                    final ViewPager viewPager = (ViewPager)rootView.findViewById(R.id.pager);
                                    final AcademicsAdapter adapter = new AcademicsAdapter(((NavActivity) getActivity()).getSupportFragmentManager(), tabLayout.getTabCount());
                                    viewPager.setAdapter(adapter);

//                                    pDialog.hide();

                                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                                    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                        @Override
                                        public void onTabSelected(TabLayout.Tab tab) {
                                            viewPager.setCurrentItem(tab.getPosition());
                                        }

                                        @Override
                                        public void onTabUnselected(TabLayout.Tab tab) {

                                        }

                                        @Override
                                        public void onTabReselected(TabLayout.Tab tab) {

                                        }
                                    });


                                }





//                                    pDialog.hide();
//                                    Toast.makeText(getActivity(), "Data Loading completed..", Toast.LENGTH_SHORT).show();


                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }


                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
//                pDialog.hide();
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
