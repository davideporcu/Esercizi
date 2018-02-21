package com.porcu.davide.flatdice.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.porcu.davide.flatdice.R;
import com.porcu.davide.flatdice.fragment.FacciaDadoFragment;
import com.porcu.davide.flatdice.fragment.WelcomeFragment;
import com.porcu.davide.flatdice.util.CubeAnimation;


public class MainActivity extends AppCompatActivity {

    final int NUM_DITA_PER_CHIUDERE = 5;

    int DURATA_ANIMAZIONE ;
    float MIN_DISTANCE = 150.0f;

    float x1, x2, y1, y2;
    long istanteInizioAnimazione;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        istanteInizioAnimazione = 0;
        DURATA_ANIMAZIONE = getResources().getInteger(R.integer.durataAnimazione);


        WelcomeFragment welcomeFragment = new WelcomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, welcomeFragment);
        ft.commit();

    }


    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() == NUM_DITA_PER_CHIUDERE) {
            this.finish();
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = event.getX();
                    y1 = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    long istanteActionUp = System.currentTimeMillis();
                    if (istanteActionUp - istanteInizioAnimazione > DURATA_ANIMAZIONE) {
                        istanteInizioAnimazione = istanteActionUp;
                        x2 = event.getX();
                        y2 = event.getY();
                        float deltaX = x2 - x1;
                        float deltaY = y2 - y1;
                        if (Math.abs(deltaX) > MIN_DISTANCE) {
                            // Left to Right swipe action
                            if (x2 > x1) {
                                changeFragment(CubeAnimation.RIGHT);
                            }
                            // Right to left swipe action
                            else {
                                changeFragment(CubeAnimation.LEFT);
                            }
                        } else if (Math.abs(deltaY) > MIN_DISTANCE) {
                            //swipe Down to Up
                            if (y2 < y1) {
                                changeFragment(CubeAnimation.UP);
                            } else {//swipe Up to Down
                                changeFragment(CubeAnimation.DOWN);
                            }
                        } /*else {
                            Toast.makeText(this, "TAP", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                    break;
            }


        }
        return super.onTouchEvent(event);
    }

    public void changeFragment(int direzioneAnimazione) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.fragmentContainer);
        Fragment nextFragment = null;
        FragmentTransaction ft = fm.beginTransaction();

        if (fm.findFragmentById(R.id.fragmentContainer) instanceof WelcomeFragment) {
            int animation = FragmentTransaction.TRANSIT_NONE;
            switch (direzioneAnimazione) {
                case CubeAnimation.UP:
                    animation = R.animator.slide_lineare_esci_verso_sopra;
                    break;
                case CubeAnimation.DOWN:
                    animation = R.animator.slide_lineare_esci_verso_sotto;
                    break;
                case CubeAnimation.RIGHT:
                    animation = R.animator.slide_lineare_esci_verso_destra;
                    break;
                case CubeAnimation.LEFT:
                    animation = R.animator.slide_lineare_esci_verso_sinistra;
                    break;
            }
            ft.setCustomAnimations(FragmentTransaction.TRANSIT_NONE, animation);
            nextFragment = FacciaDadoFragment.newInstance();
        } else {

            FacciaDadoFragment facciaDadoFragment = (FacciaDadoFragment) currentFragment;
            nextFragment = FacciaDadoFragment.newInstance(facciaDadoFragment.getNumFacciaDado(), direzioneAnimazione);
            facciaDadoFragment.setDirezioneAnimazione(direzioneAnimazione);

        }
        ft.replace(R.id.fragmentContainer, nextFragment);
        ft.commit();
    }
}
