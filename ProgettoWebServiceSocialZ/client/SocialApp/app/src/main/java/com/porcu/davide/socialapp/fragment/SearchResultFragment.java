package com.porcu.davide.socialapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.adapter.SearchedGroupAdapter;
import com.porcu.davide.socialapp.adapter.SearchedUserAdapter;
import com.porcu.davide.socialapp.model.StructuredSearchedGroup;
import com.porcu.davide.socialapp.model.StructuredSearchedUser;
import com.porcu.davide.socialapp.task.GetListaSearchedGroupAsyncTask;
import com.porcu.davide.socialapp.task.GetListaSearchedUserAsyncTask;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment implements BackgroundTaskState {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_QUERY = "paramStringQuery";

    // TODO: Rename and change types of parameters
    private String query;

    private GetListaSearchedUserAsyncTask getListaSearchedUserAsyncTask;
    private GetListaSearchedGroupAsyncTask getListaSearchedGroupAsyncTask;
    private TabLayout tabLayout;
    private RecyclerView rv;

    private SearchedUserAdapter adapterUser;
    private SearchedGroupAdapter adapterGroup;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    //factory method with parameters
    public static SearchResultFragment newInstance(String query) {//numero sulla faccia precedente
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String queryArg = getArguments().getString(ARG_PARAM_QUERY);
            this.query = queryArg;
            getListaSearchedUserAsyncTask = new GetListaSearchedUserAsyncTask(this, query);
            getListaSearchedGroupAsyncTask = new GetListaSearchedGroupAsyncTask(this, query);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.search_result_fragment_layout, container, false);

        rv = fragmentView.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        tabLayout = fragmentView.findViewById(R.id.tabLayoutSearch);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //do stuff here
                Log.d("TAB SELECTED ", tab.getPosition() + "");
                doQuery();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        doQuery();
    }

    @Override
    public void onBackgroundTaskCompleted(Object result) {
        if (result != null) {
            switch (tabLayout.getSelectedTabPosition()) {
                case 0:
                    ArrayList<StructuredSearchedUser> searchedUsers = (ArrayList<StructuredSearchedUser>) result;
                    SearchedUserAdapter adapterUser = new SearchedUserAdapter(searchedUsers, this.getActivity());
                    rv.setAdapter(adapterUser);
                    break;
                case 1:
                    ArrayList<StructuredSearchedGroup> searchedGroups = (ArrayList<StructuredSearchedGroup>) result;
                    SearchedGroupAdapter adapterGroup = new SearchedGroupAdapter(searchedGroups, this.getActivity());
                    rv.setAdapter(adapterGroup);
                    break;
            }
        }
    }

    public void doNewQuery(String query) {
        this.query = query;
        getListaSearchedUserAsyncTask.cancel(true);//fermo tutte le ricerche
        getListaSearchedGroupAsyncTask.cancel(true);
        adapterUser = null;
        adapterGroup = null;
        doQuery();
    }

    private void doQuery() {
        switch (tabLayout.getSelectedTabPosition()) {
            case 0://Utenti
                if (adapterUser == null) {//mai cercato
                    Log.d("DOQUERY ", "UTENTE");
                    getListaSearchedGroupAsyncTask.cancel(true);
                    getListaSearchedUserAsyncTask = new GetListaSearchedUserAsyncTask(this, query);
                    getListaSearchedUserAsyncTask.execute();
                } else {
                    rv.setAdapter(adapterUser);
                }
                break;
            case 1://Gruppi
                if (adapterGroup == null) {//mai cercato
                    Log.d("DOQUERY ", "GRUPPO");
                    getListaSearchedUserAsyncTask.cancel(true);
                    getListaSearchedGroupAsyncTask = new GetListaSearchedGroupAsyncTask(this, query);
                    getListaSearchedGroupAsyncTask.execute();
                } else {
                    rv.setAdapter(adapterGroup);
                }
                break;

        }

    }
}
