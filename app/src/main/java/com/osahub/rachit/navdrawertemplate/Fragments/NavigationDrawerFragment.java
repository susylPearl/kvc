package com.osahub.rachit.navdrawertemplate.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Adapters.NavigationDrawerAdapter;
import com.osahub.rachit.navdrawertemplate.Classes.AppController;
import com.osahub.rachit.navdrawertemplate.Classes.MyLinearLayoutManager;
import com.osahub.rachit.navdrawertemplate.Interfaces.NavigationDrawerCallbacks;
import com.osahub.rachit.navdrawertemplate.Classes.NavigationItem;
import com.osahub.rachit.navdrawertemplate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends android.support.v4.app.Fragment implements NavigationDrawerCallbacks {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private View mFragmentContainerView;
    private RelativeLayout relativeLayout;
    private ImageButton button;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final List<NavigationItem> items = new ArrayList<NavigationItem>();

        final View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mDrawerList = (RecyclerView) view.findViewById(R.id.drawerList);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rel_lay);
//        button = (ImageButton) view.findViewById(R.id.imageButton);

        ImageView imageView = (ImageView) view.findViewById(R.id.header_image);

        final NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(items);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter.setNavigationDrawerCallbacks(this);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               return;
            }
        });





//        Toast.makeText(getActivity(), "Data Loading Started..",Toast.LENGTH_SHORT).show();

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
        String url = "http://demo.peacenepal.com/kvc/college/api/showMenu";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        items.clear();

                        if (response != null){
                            try {
                                JSONObject jsonObject = new JSONObject(String.valueOf(response));
                                JSONObject jo = jsonObject.getJSONObject("0");
                                Iterator<String> iterator = jo.keys();
                                List<String> mItems = new ArrayList<String>();

                                while (iterator.hasNext()) {
                                    String currentKey = iterator.next();
                                    Log.d(TAG, currentKey);
                                    mItems.add(currentKey);
                                    
//                                    items.add(new NavigationItem(currentKey, ContextCompat.getDrawable(getActivity(), R.drawable.forward)));

                                }

                                String[] itemArray = mItems.toArray(new String[mItems.size()]);

                                items.add(new NavigationItem("Home", ContextCompat.getDrawable(getActivity(), R.drawable.about)));
                                items.add(new NavigationItem(itemArray[0], ContextCompat.getDrawable(getActivity(), R.drawable.abt)));
                                items.add(new NavigationItem(itemArray[7], ContextCompat.getDrawable(getActivity(), R.drawable.academics)));
                                items.add(new NavigationItem(itemArray[8], ContextCompat.getDrawable(getActivity(), R.drawable.service)));

                                items.add(new NavigationItem("News And Notices", ContextCompat.getDrawable(getActivity(), R.drawable.news)));
                                items.add(new NavigationItem("Events And Activities", ContextCompat.getDrawable(getActivity(), R.drawable.events)));
                                items.add(new NavigationItem("Gallery", ContextCompat.getDrawable(getActivity(), R.drawable.gallery)));
                                items.add(new NavigationItem("Contact", ContextCompat.getDrawable(getActivity(), R.drawable.contact)));

                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                mDrawerList.setLayoutManager(layoutManager);
                                mDrawerList.setHasFixedSize(true);

                                mDrawerList.setAdapter(adapter);
                                selectItem(mCurrentSelectedPosition);


                                    pDialog.hide();
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



        return view;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return mActionBarDrawerToggle;
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        selectItem(position);
    }


    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     * @param toolbar      The Toolbar of the activity.
     */
    public void setup(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.myPrimaryDarkColor));


        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) return;

                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) return;
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }
                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }

        ((NavigationDrawerAdapter) mDrawerList.getAdapter()).selectPosition(position);
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

//    public void setUserData(String user, String email, Bitmap avatar) {
////        ImageView avatarContainer = (ImageView) mFragmentContainerView.findViewById(R.id.imgAvatar);
////        ((TextView) mFragmentContainerView.findViewById(R.id.txtUserEmail)).setText(email);
////        ((TextView) mFragmentContainerView.findViewById(R.id.txtUsername)).setText(user);
////        avatarContainer.setImageDrawable(new RoundImage(avatar));
//    }

    public View getGoogleDrawer() {
        return mFragmentContainerView.findViewById(R.id.googleDrawer);
    }



//    public static class RoundImage extends Drawable {
//        private final Bitmap mBitmap;
//        private final Paint mPaint;
//        private final RectF mRectF;
//        private final int mBitmapWidth;
//        private final int mBitmapHeight;
//
//        public RoundImage(Bitmap bitmap) {
//            mBitmap = bitmap;
//            mRectF = new RectF();
//            mPaint = new Paint();
//            mPaint.setAntiAlias(true);
//            mPaint.setDither(true);
//            final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//            mPaint.setShader(shader);
//
//            mBitmapWidth = mBitmap.getWidth();
//            mBitmapHeight = mBitmap.getHeight();
//        }
//
//        @Override
//        public void draw(Canvas canvas) {
//            canvas.drawOval(mRectF, mPaint);
//        }
//
//        @Override
//        protected void onBoundsChange(Rect bounds) {
//            super.onBoundsChange(bounds);
//            mRectF.set(bounds);
//        }
//
//        @Override
//        public void setAlpha(int alpha) {
//            if (mPaint.getAlpha() != alpha) {
//                mPaint.setAlpha(alpha);
//                invalidateSelf();
//            }
//        }
//
//        @Override
//        public void setColorFilter(ColorFilter cf) {
//            mPaint.setColorFilter(cf);
//        }
//
//        @Override
//        public int getOpacity() {
//            return PixelFormat.TRANSLUCENT;
//        }
//
//        @Override
//        public int getIntrinsicWidth() {
//            return mBitmapWidth;
//        }
//
//        @Override
//        public int getIntrinsicHeight() {
//            return mBitmapHeight;
//        }
//
//        public void setAntiAlias(boolean aa) {
//            mPaint.setAntiAlias(aa);
//            invalidateSelf();
//        }
//
//        @Override
//        public void setFilterBitmap(boolean filter) {
//            mPaint.setFilterBitmap(filter);
//            invalidateSelf();
//        }
//
//        @Override
//        public void setDither(boolean dither) {
//            mPaint.setDither(dither);
//            invalidateSelf();
//        }
//
//        public Bitmap getBitmap() {
//            return mBitmap;
//        }
//
//    }
//
//
//
//    private void scaleImage(ImageView view) throws NoSuchElementException  {
//        // Get bitmap from the the ImageView.
//        Bitmap bitmap = null;
//
//        try {
//            Drawable drawing = view.getDrawable();
//            bitmap = ((BitmapDrawable) drawing).getBitmap();
//        } catch (NullPointerException e) {
//            throw new NoSuchElementException("No drawable on given view");
//        } catch (ClassCastException e) {
//            // Check bitmap is Ion drawable
////            bitmap = Ion.with(view).getBitmap();
//        }
//
//        // Get current dimensions AND the desired bounding box
//        int width = 0;
//
//        try {
//            width = bitmap.getWidth();
//        } catch (NullPointerException e) {
//            throw new NoSuchElementException("Can't find bitmap on given view/drawable");
//        }
//
//        int height = bitmap.getHeight();
//        int bounding = dpToPx(300);
//        Log.i("Test", "original width = " + Integer.toString(width));
//        Log.i("Test", "original height = " + Integer.toString(height));
//        Log.i("Test", "bounding = " + Integer.toString(bounding));
//
//        // Determine how much to scale: the dimension requiring less scaling is
//        // closer to the its side. This way the image always stays inside your
//        // bounding box AND either x/y axis touches it.
//        float xScale = ((float) bounding) / width;
//        float yScale = ((float) bounding) / height;
//        float scale = (xScale <= yScale) ? xScale : yScale;
//        Log.i("Test", "xScale = " + Float.toString(xScale));
//        Log.i("Test", "yScale = " + Float.toString(yScale));
//        Log.i("Test", "scale = " + Float.toString(scale));
//
//        // Create a matrix for the scaling and add the scaling data
//        Matrix matrix = new Matrix();
//        matrix.postScale(scale, scale);
//
//        // Create a new bitmap and convert it to a format understood by the ImageView
//        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//        width = scaledBitmap.getWidth(); // re-use
//        height = scaledBitmap.getHeight(); // re-use
//        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
//        Log.i("Test", "scaled width = " + Integer.toString(width));
//        Log.i("Test", "scaled height = " + Integer.toString(height));
//
//        // Apply the scaled bitmap
//        view.setImageDrawable(result);
//
//        // Now change ImageView's dimensions to match the scaled image
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
//        params.width = width;
//        params.height = height;
//        view.setLayoutParams(params);
//
//        Log.i("Test", "done");
//    }
//
//    private int dpToPx(int dp) {
//        float density = getActivity().getResources().getDisplayMetrics().density;
//        return Math.round((float)dp * density);
//    }
}
