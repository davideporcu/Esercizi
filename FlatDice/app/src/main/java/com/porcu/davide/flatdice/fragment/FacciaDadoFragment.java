package com.porcu.davide.flatdice.fragment;


import android.animation.Animator;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.widget.ImageView;

import com.porcu.davide.flatdice.R;
import com.porcu.davide.flatdice.util.CubeAnimation;

import java.util.Random;

public class FacciaDadoFragment extends Fragment {


    private static final Random random = new Random();
    private static final int NUM_FACCE_DADO = 6;//ogni faccia un numero diverso

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_NUMFACCIADADO = "paramNumFacciaDado";
    private static final String ARG_PARAM_DIREZIONEANIMAZIONE = "paramDirezioneAnimazione";

    // TODO: Rename and change types of parameters
    private int numFacciaDado;
    private int direzioneAnimazione;

    public FacciaDadoFragment() {
        // Required empty public constructor
    }

    public static FacciaDadoFragment newInstance() {
        FacciaDadoFragment fragment = new FacciaDadoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_NUMFACCIADADO, random.nextInt(NUM_FACCE_DADO) + 1);//un dado non ha faccia con zero
        args.putInt(ARG_PARAM_DIREZIONEANIMAZIONE, CubeAnimation.UP);
        fragment.setArguments(args);
        return fragment;
    }

    //factory method with parameters
    public static FacciaDadoFragment newInstance(int num, int direzioneAnimazione) {//numero sulla faccia precedente
        FacciaDadoFragment fragment = new FacciaDadoFragment();
        Bundle args = new Bundle();
        int nuovoNum;
        do {
            nuovoNum = random.nextInt(NUM_FACCE_DADO) + 1;
        } while (nuovoNum == num);//se uguale, lo rigenero

        args.putInt(ARG_PARAM_NUMFACCIADADO, nuovoNum);
        args.putInt(ARG_PARAM_DIREZIONEANIMAZIONE, direzioneAnimazione);
        fragment.setArguments(args);
        return fragment;
    }

    public int getNumFacciaDado() {
        return numFacciaDado;
    }

    public void setDirezioneAnimazione(int direzioneAnimazione) {
        this.direzioneAnimazione = direzioneAnimazione;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numFacciaDado = getArguments().getInt(ARG_PARAM_NUMFACCIADADO);
            direzioneAnimazione = getArguments().getInt(ARG_PARAM_DIREZIONEANIMAZIONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_faccia_dado, container, false);

        ImageView imgFaccia = fragmentView.findViewById(R.id.imgFacciaDado);
        Context context = imgFaccia.getContext();
        int id =context.getResources().getIdentifier("ic_dado" + numFacciaDado , "drawable", context.getPackageName());
        imgFaccia.setImageResource(id);

        return fragmentView;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean isEntering, int nextAnim) {

        return CubeAnimation.create(direzioneAnimazione, isEntering, getResources().getInteger(R.integer.durataAnimazione));
    }
}
