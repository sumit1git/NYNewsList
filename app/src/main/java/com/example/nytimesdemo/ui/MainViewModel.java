package com.example.nytimesdemo.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nytimesdemo.data.model.NewsData;
import com.example.nytimesdemo.data.remote.NetworkService;
import com.example.nytimesdemo.data.remote.response.PopularNewsResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private String TAG = MainViewModel.class.getSimpleName();
    private NetworkService networkService;
    protected MutableLiveData<List<NewsData>> popularNewsList;
      protected  List<NewsData> newsModelData;
    public MainViewModel() {
        super();
        //  popularNewsList = new MutableLiveData<>();
    }

    protected void setNetworkService(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
       // popularNewsList.d
    }

    protected void callPopularNewsList() {
        popularNewsList = new MutableLiveData<>();
        newsModelData = new ArrayList<>();

        networkService.getPopularNewsList().enqueue(new Callback<PopularNewsResponseModel>() {
            @Override
            public void onResponse(Call<PopularNewsResponseModel> call, Response<PopularNewsResponseModel> response) {
               List<PopularNewsResponseModel.ResultsBean> resultsBeans =response.body().getResults();
               for(PopularNewsResponseModel.ResultsBean result :resultsBeans){
                  String url = result.getMedia().get(0).getMediametadata().get(0).getUrl();
                   String title=  result.getTitle();
                     String byLine = result.getByline();
                     String publishedDate = result.getPublished_date();
                   newsModelData.add(new NewsData(title,byLine,publishedDate,url));
               }
                popularNewsList.postValue(newsModelData);
            }

            @Override
            public void onFailure(Call<PopularNewsResponseModel> call, Throwable t) {

            }
        });
    }
}
      //  });


  //  }
