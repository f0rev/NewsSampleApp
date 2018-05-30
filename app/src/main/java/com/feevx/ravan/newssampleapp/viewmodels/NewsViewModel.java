package com.feevx.ravan.newssampleapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.feevx.ravan.newssampleapp.api.response.NewsItem;
import com.feevx.ravan.newssampleapp.repositories.NewsRepository;

import java.util.List;

import javax.inject.Inject;

public class NewsViewModel extends ViewModel {

    private NewsRepository mNewsRepository;
    private LiveData<List<NewsItem>> mAllNews;
    private LiveData<List<NewsItem>> mFootballNews;
    private LiveData<List<NewsItem>> mBasketballNews;
    private LiveData<List<NewsItem>> mVolleyballNews;
    private LiveData<List<NewsItem>> mHockeyNews;
    private final MutableLiveData<NewsItem> mSelectedNews = new MutableLiveData<NewsItem>();

    @Inject
    public NewsViewModel(@NonNull NewsRepository newsRepository) {
        mNewsRepository = newsRepository;
    }

    public LiveData<List<NewsItem>> getAllNews() { return mAllNews; }

    public LiveData<List<NewsItem>> getFootballNews() {
        return mFootballNews;
    }

    public LiveData<List<NewsItem>> getBasketballNews() {
        return mBasketballNews;
    }

    public LiveData<List<NewsItem>> getVolleyballNews() {
        return mVolleyballNews;
    }

    public LiveData<List<NewsItem>> getHockeyNews() {
        return mHockeyNews;
    }

    public void init(int newsCategory) {
        loadNews(newsCategory);
    }

    public void setSelectedNews(NewsItem newsItem) {
        mSelectedNews.setValue(newsItem);
    }

    public MutableLiveData<NewsItem> getSelectedNews() {
        return mSelectedNews;
    }

    private void loadNews(int newsCategory) {

        switch (newsCategory) {
            case 0:
                mAllNews = mNewsRepository.getNews(newsCategory);
                break;
            case 1:
                mFootballNews = mNewsRepository.getNews(newsCategory);
                break;
            case 2:
                mBasketballNews = mNewsRepository.getNews(newsCategory);
                break;
            case 3:
                mVolleyballNews = mNewsRepository.getNews(newsCategory);
                break;
            case 4:
                mHockeyNews = mNewsRepository.getNews(newsCategory);
                break;
        }

    }


}
