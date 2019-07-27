package com.example.nytimesdemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
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
}
