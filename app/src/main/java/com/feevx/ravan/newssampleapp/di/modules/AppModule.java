package com.feevx.ravan.newssampleapp.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.feevx.ravan.newssampleapp.api.NewsWebservice;
import com.feevx.ravan.newssampleapp.internet.ConnectionLiveData;
import com.feevx.ravan.newssampleapp.repositories.NewsRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class AppModule {

    //repository injection
    @Provides
    @Singleton
    NewsRepository provideNewsRepository(NewsWebservice webservice) {
        return new NewsRepository(webservice);
    }

    //network injection
    private static String BASE_URL = "http://104.131.34.204/";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    NewsWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(NewsWebservice.class);
    }

    @Provides
    @Singleton
    ConnectionLiveData provideConnectionLiveData(Application application){
        return new ConnectionLiveData( application);
    }

}
