package com.example.nytimesdemo.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.nytimesdemo.R;
import com.example.nytimesdemo.data.remote.NetworkService;
import com.example.nytimesdemo.data.remote.Networking;
import com.example.nytimesdemo.ui.fragment.DetailFragment;
import com.example.nytimesdemo.ui.fragment.MainFragment;
public class MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener {

    public  NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setUpView();
        networkService = Networking.getClient().create(NetworkService.class);
        addFragment(R.id.container_fragment,MainFragment.newInstance(),MainFragment.FRAGMENT_TAG);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {
     replaceFragment(R.id.container_fragment, DetailFragment.newInstance(bundle),DetailFragment.FRAGMENT_TAG,null);
    }
}
