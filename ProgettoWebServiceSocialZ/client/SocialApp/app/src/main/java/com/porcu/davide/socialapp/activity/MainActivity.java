package com.porcu.davide.socialapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.adapter.SearchedUserAdapter;
import com.porcu.davide.socialapp.adapter.UserAdapter;
import com.porcu.davide.socialapp.fragment.HomeFragment;
import com.porcu.davide.socialapp.fragment.SearchResultFragment;
import com.porcu.davide.socialapp.fragment.UserPageFragment;
import com.porcu.davide.socialapp.util.Util;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements FragmentEventListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBar actionbar;
    private MenuItem searchItem;

    private FragmentManager fragmentManager;

    private Stack<Fragment> fragmentVisitati;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref=  getSharedPreferences("mieImpostazioni", Context.MODE_PRIVATE);
        String indirizzo=sharedPref.getString("indirizzo","");
        String porta=sharedPref.getString("porta","");

        Util.setIndirizzoPortaServer(indirizzo,porta);

        fragmentVisitati = new Stack<>();

        fragmentManager = getFragmentManager();
        nextFragment(HomeFragment.newInstance());

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // set item as selected to persist highlight
                //menuItem.setChecked(true);
                // close drawer when item is tapped
                mDrawerLayout.closeDrawers();
                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here
                selectDrawerItem(menuItem);
                return true;
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentVisitati.size() > 1) {
            goToPreviousFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item_search, menu);
        searchItem = menu.findItem(R.id.action_search);

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //crea qui il fragment per la ricerca
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.d("CLOSE-> ", "SEARCH BOX");
                if (fragmentVisitati.size() > 1) {
                    goToPreviousFragment();
                }
                return true;
            }
        });

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                //getta il testo e mostra il fragment della ricerca con due tab (una per persone e una per gruppi e hobby???)

                Log.d("- § - § - § - CERCATO: ", query);

                Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
                if (currentFragment instanceof SearchResultFragment) {
                    SearchResultFragment searchResultFragment = (SearchResultFragment) currentFragment;
                    searchResultFragment.doNewQuery(query);
                } else {
                    nextFragment(SearchResultFragment.newInstance(query));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        // Expand the search view and request focus
        //searchItem.expandActionView();
        //searchView.requestFocus();

        return super.onCreateOptionsMenu(menu);
    }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        //ListaPraticantiHobbyFragment fragment = null;
        //Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentVisitati.removeAllElements();
                if (searchItem.isActionViewExpanded()) {
                    searchItem.collapseActionView();
                }
                nextNotTrackedFragment(HomeFragment.newInstance());
                break;
            case R.id.nav_impostazioni:
                Intent i = new Intent(this, ImpostazioniActivity.class);
                startActivity(i);
                break;
        }

        /*try {
            fragment = (ListaPraticantiHobbyFragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();*/

    }

    private void nextNotTrackedFragment(Fragment nextFragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragmentContainer, nextFragment);
        ft.commit();
    }

    private void nextFragment(Fragment nextFragment) {
        Fragment precedente = fragmentManager.findFragmentById(R.id.fragmentContainer);
        fragmentVisitati.push(precedente);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragmentContainer, nextFragment);
        ft.commit();
    }

    private void goToPreviousFragment() {
        Fragment precedente = fragmentVisitati.pop();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragmentContainer, precedente);
        ft.commit();
        if (precedente instanceof UserPageFragment) {
            UserPageFragment userPageFragment = (UserPageFragment) precedente;
            toolbar.setTitle(userPageFragment.getNomeCognome());
        } else {
            toolbar.setTitle(getResources().getString(R.string.app_name));
        }

    }


    @Override
    public void fragmentEventHadler(Object... objects) {
        String nomeCognomeOfSelectedUser = objects[0].toString();
        String usernameOfSelectedUser = objects[1].toString();
        UserPageFragment upf = UserPageFragment.newInstance(usernameOfSelectedUser, nomeCognomeOfSelectedUser);
        nextFragment(upf);
        searchItem.collapseActionView();
        toolbar.setTitle(nomeCognomeOfSelectedUser);
    }
}
