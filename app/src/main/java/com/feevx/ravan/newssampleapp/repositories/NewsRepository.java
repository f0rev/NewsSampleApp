package com.feevx.ravan.newssampleapp.repositories;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.feevx.ravan.newssampleapp.api.NewsWebservice;
import com.feevx.ravan.newssampleapp.api.response.ApiResponse;
import com.feevx.ravan.newssampleapp.api.response.NewsItem;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Philippe on 02/03/2018.
 */

@Singleton
public class NewsRepository {

    public static int ALL_NEWS = 0;
    public static int FOOTBALL = 1;
    public static int BASKETBALL = 2;
    public static int VOLLEYBALL = 3;
    public static int HOCKEY = 4;


    private final NewsWebservice mWebservice;

    @Inject
    public NewsRepository(NewsWebservice webservice) {
        mWebservice = webservice;
    }

    @SuppressLint("CheckResult")
    public LiveData<List<NewsItem>> getNews(int newsCategory) {
        MutableLiveData<List<NewsItem>> newsMutableLiveData = new MutableLiveData<>();
        Single<ApiResponse> call = null;
        switch (newsCategory) {
            case 0:
                call = mWebservice.getAllNews();
                break;
            case 1:
                call = mWebservice.getFootballNews();
                break;
            case 2:
                call = mWebservice.getBasketballNews();
                break;
            case 3:
                call = mWebservice.getVolleyballNews();
                break;
            case 4:
                call = mWebservice.getHockeyNews();
                break;
        }

        assert call != null;
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {
                            List<NewsItem> coffeeItems = apiResponse.getData();
                            newsMutableLiveData.setValue(coffeeItems);
                        },
                        throwable -> Log.e("Error ",  "while getting news:  " +throwable.getLocalizedMessage()));
        return newsMutableLiveData;
    }

}
