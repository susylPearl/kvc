package com.osahub.rachit.navdrawertemplate.Fragments.StudentService;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
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
import com.osahub.rachit.navdrawertemplate.Classes.AppController;
import com.osahub.rachit.navdrawertemplate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sushil on 1/25/16.
 */
public class FacilitiesFragment extends Fragment implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6;
    public static final int click = 123;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.tab_fragment_5, container, false);

        btn1 = (Button) rootView.findViewById(R.id.btn1);
        btn2 = (Button) rootView.findViewById(R.id.btn2);
        btn3 = (Button) rootView.findViewById(R.id.btn3);
        btn4 = (Button) rootView.findViewById(R.id.btn4);
        btn5 = (Button) rootView.findViewById(R.id.btn5);
        btn6 = (Button) rootView.findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

//        final ProgressDialog pDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
//        pDialog.setIndeterminate(true);
//        pDialog.setMessage("Loading...");
//        pDialog.show();


//        final List<NavigationItem> navigationItems = getMenu();
//
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
                        try {
                            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

                            if (response != null){
                                try {
                                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                                    JSONObject jo = jsonObject.getJSONObject("0");
                                    JSONArray ja = jo.getJSONArray("Student Service");
                                    JSONObject jobj = ja.getJSONObject(0);
                                    JSONArray jar = jobj.getJSONArray("children");
                                    JSONObject jsonObject1 = jar.getJSONObject(0);

                                JSONArray jsonArray = jsonObject1.getJSONArray("Facilties");
                                JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                                JSONArray jsonArray1 = jsonObject2.getJSONArray("children");
                                JSONObject jsonObject3 = jsonArray1.getJSONObject(0);


                                    Iterator<String> iterator = jsonObject3.keys();

                                    List<String> keysList = new ArrayList<String>();

                                    while (iterator.hasNext()){
                                        String item = iterator.next();
                                        keysList.add(item);

                                    }

                                    String[] keysArray = keysList.toArray(new String[keysList.size()]);



                                    btn1.setText(keysArray[0]);
                                    btn2.setText(keysArray[1]);
                                    btn3.setText(keysArray[2]);
                                    btn4.setText(keysArray[3]);
                                    btn5.setText(keysArray[4]);
                                    btn6.setText(keysArray[5]);



//                                    pDialog.hide();

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

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), NavActivity.class);


        if (view == btn1){
            intent.putExtra("pos", 10);
            startActivity(intent);
        }
        else if (view == btn2){
            intent.putExtra("pos", 11);
            startActivity(intent);

        }
        else if (view == btn3){
            intent.putExtra("pos", 12);
            startActivity(intent);
        }
        else if (view == btn4){
            intent.putExtra("pos", 13);
            startActivity(intent);
        }
        else if (view == btn5){
            intent.putExtra("pos", 14);
            startActivity(intent);
        }
        else if (view == btn6){
            intent.putExtra("pos", 15);
            startActivity(intent);
        }
    }
}
