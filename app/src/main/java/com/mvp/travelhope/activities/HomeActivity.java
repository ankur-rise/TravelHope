package com.mvp.travelhope.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mvp.travelhope.R;
import com.mvp.travelhope.fragments.HomeFragment;
import com.mvp.travelhope.fragments.SearchFragment;

public class HomeActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        inflateFragment();
    }

    private void inflateFragment() {
        getFragmentManager().beginTransaction().add(R.id.container, HomeFragment.newInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // callback from HomeFragment argument recommendation is to define type of search like if true the we'll show
    // user to choose origin and on
    // behalf of that we'll show a list of destination nearby if false then user know where to go.
    @Override
    public void onOptionSelected(boolean recommendation) {
        inflateSearchFragment(recommendation);
    }

    private void inflateSearchFragment(boolean recommendation) {
        getFragmentManager().beginTransaction().replace(R.id.container, SearchFragment.newInstance(recommendation))
                .addToBackStack(null).commit();
    }

    // callback from Searchfragment
    @Override
    public void onFragmentInteraction() {

    }
}
