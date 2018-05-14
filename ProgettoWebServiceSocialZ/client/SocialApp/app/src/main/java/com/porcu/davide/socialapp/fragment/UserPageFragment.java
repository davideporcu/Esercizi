package com.porcu.davide.socialapp.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.adapter.HobbyAdapter;
import com.porcu.davide.socialapp.adapter.InfoUtenteAdapter;
import com.porcu.davide.socialapp.adapter.StructuredHobbyAdapter;
import com.porcu.davide.socialapp.model.Hobby;
import com.porcu.davide.socialapp.model.InfoUtente;
import com.porcu.davide.socialapp.model.StructuredHobby;
import com.porcu.davide.socialapp.task.GetImgProfiloUtenteAsyncTask;
import com.porcu.davide.socialapp.task.GetInfoUtenteAsyncTask;
import com.porcu.davide.socialapp.task.GetListaHobbyPraticatiDaUtente;

import java.util.ArrayList;

public class UserPageFragment extends Fragment implements BackgroundTaskState {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_NOMECOGNOME = "paramStringNomeCognome";
    private static final String ARG_PARAM_USERNAME = "paramStringUsername";


    // TODO: Rename and change types of parameters

    private String nomeCognome;
    private String username;

    private InfoUtenteAdapter infoUtenteAdapter;
    private StructuredHobbyAdapter structuredHobbyAdapter;

    private TabLayout tabLayout;
    private RecyclerView rv;
    private ImageView imgProfilo;


    public UserPageFragment() {
        // Required empty public constructor
    }

    public static UserPageFragment newInstance() {
        UserPageFragment fragment = new UserPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //factory method with parameters
    public static UserPageFragment newInstance(String username, String nomeCognome) {//numero sulla faccia precedente
        UserPageFragment fragment = new UserPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_NOMECOGNOME, nomeCognome);
        args.putString(ARG_PARAM_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_PARAM_USERNAME);
            nomeCognome = getArguments().getString(ARG_PARAM_NOMECOGNOME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.user_page_fragment_layout, container, false);
        /*ImageView imgHeader = fragmentView.findViewById(R.id.imgHeader);
        imgHeader.setImageBitmap(infoUtente.getBtmpImgProfilo());*/

        imgProfilo = fragmentView.findViewById(R.id.imgHeader);

        rv = fragmentView.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        tabLayout = fragmentView.findViewById(R.id.tabLayoutUserPage);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //do stuff here
                Log.d("TAB SELECTED ", tab.getPosition() + "");
                showDataOfSelectedTab();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new GetImgProfiloUtenteAsyncTask(this, username).execute();
        new GetInfoUtenteAsyncTask(this, username).execute();
        new GetListaHobbyPraticatiDaUtente(this, username).execute();

        return fragmentView;
    }

    @Override
    public void onBackgroundTaskCompleted(Object result) {
        if (result != null) {
            if (result instanceof Bitmap) {
                imgProfilo.setImageBitmap((Bitmap) result);
            } else if (result instanceof InfoUtente) {
                InfoUtente infoUtente = (InfoUtente) result;
                ArrayList<Pair<String, String>> listaInfo = new ArrayList<>();
                listaInfo.add(new Pair<String, String>("Sesso", infoUtente.getSesso()));
                listaInfo.add(new Pair<String, String>("Data di nascita", infoUtente.getDataDiNascita()));
                listaInfo.add(new Pair<String, String>("Email", infoUtente.getEmail()));
                listaInfo.add(new Pair<String, String>("Telefono", infoUtente.getTelefono()));
                listaInfo.add(new Pair<String, String>("Indirizzo", infoUtente.getIndirizzo()));

                infoUtenteAdapter = new InfoUtenteAdapter(listaInfo, this.getActivity());

                if (tabLayout.getSelectedTabPosition() == 0) {
                    rv.setAdapter(infoUtenteAdapter);
                }
            } else {//è per forza l'ArrayList<StructuredHobby>
                ArrayList<StructuredHobby> listaHobby = (ArrayList<StructuredHobby>) result;
                structuredHobbyAdapter = new StructuredHobbyAdapter(listaHobby, this.getActivity());
                if (tabLayout.getSelectedTabPosition() == 1) {
                    rv.setAdapter(structuredHobbyAdapter);
                }
            }
        }
    }

    private void showDataOfSelectedTab() {
        switch (tabLayout.getSelectedTabPosition()) {
            case 0:
                rv.setAdapter(infoUtenteAdapter);
                break;
            case 1:
                rv.setAdapter(structuredHobbyAdapter);
                break;
        }
    }

    public String getNomeCognome(){
        return nomeCognome;
    }

}
