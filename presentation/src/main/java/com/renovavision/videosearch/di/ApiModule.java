package com.renovavision.videosearch.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vyng.videosearch.data.BuildConfig;
import com.renovavision.videosearch.data.api.GiphySearchApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */

@Module
public class ApiModule {

    private static final long HTTP_TIMEOUT = 60;
    private static final String GIPHY_RETROFIT = "GiphyRetrofit";
    private static final String GIPHY_OKHTTP_CLIENT = "GiphyHttpClient";

    @Singleton
    @Named(GIPHY_OKHTTP_CLIENT)
    @Provides
    public OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            HttpUrl url = chain.request().url()
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.GIPHY_ACCESS_TOKEN)
                    .build();
            Request request = chain.request().newBuilder().url(url).build();
            return chain.proceed(request);
        });

        builder.readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    @Singleton
    @Provides
    public Gson providesGson() {
        return new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();
    }

    @Singleton
    @Named(GIPHY_RETROFIT)
    @Provides
    public Retrofit provideRetrofit(@Named(GIPHY_OKHTTP_CLIENT) OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.GIPHY_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .validateEagerly(true)
                .build();
    }

    @Singleton
    @Provides
    public GiphySearchApiService giphySearchApiService(@Named(GIPHY_RETROFIT) Retrofit retrofit) {
        return retrofit.create(GiphySearchApiService.class);
    }
}
