package com.osahub.rachit.navdrawertemplate.Fragments.Gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Activities.NavActivity;
import com.osahub.rachit.navdrawertemplate.Adapters.PhotoAdapter;
import com.osahub.rachit.navdrawertemplate.Classes.ItemOffsetDecoration;
import com.osahub.rachit.navdrawertemplate.Classes.PhotoAlbum;
import com.osahub.rachit.navdrawertemplate.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushil on 2/12/16.
 */
public class GalleryItemShowFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {



    private RecyclerView mRecycleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView = null;
    private String[] urls;
    private int id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        ((NavActivity)getActivity()).setTitle("KVC Photos");

        rootView = inflater.inflate(R.layout.fragment_academics_program, container, false);

        urls = this.getArguments().getStringArray("images");
        id = this.getArguments().getInt("id");

        mRecycleView = (RecyclerView) rootView.findViewById(R.id.recyclerview_grid);
        mRecycleView.setLayoutManager(new GridLayoutManager((NavActivity)getActivity(), 2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration((NavActivity)getActivity(), R.dimen.item_offset);
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
        PhotoAdapter adapter = new PhotoAdapter((NavActivity)getActivity(), id, urls);
        mRecycleView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }
}
