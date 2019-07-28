package com.example.nytimesdemo.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nytimesdemo.R;
import com.example.nytimesdemo.data.model.NewsData;
import com.example.nytimesdemo.data.remote.NetworkService;
import com.example.nytimesdemo.data.remote.Networking;
import com.example.nytimesdemo.data.remote.response.PopularNewsResponseModel;
import com.example.nytimesdemo.ui.adapter.NewsListAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NetworkService networkService;
    MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsListAdapter mAdapter;
    private List<NewsData> newsDataList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        recyclerView =(RecyclerView)findViewById(R.id.recyclerview);
        progressBar =(ProgressBar)findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter =new NewsListAdapter(this,newsDataList);
        recyclerView.setAdapter(mAdapter);


        networkService = Networking.getClient().create(NetworkService.class);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setNetworkService(networkService);
        mainViewModel.callPopularNewsList();
        mainViewModel.popularNewsList.observe(this, new Observer<List<NewsData>>() {
            @Override
            public void onChanged(List<NewsData> newsData) {
                progressBar.setVisibility(View.GONE);
                newsDataList=newsData;
                mAdapter.updateReceiptsList(newsDataList);
            }
        });
    }

    private void setUpView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Setup Navigation Drawer Layout
        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
