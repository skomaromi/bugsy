package com.github.skomaromi.bugsy.networking;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitUtility {
    private static Retrofit sRetrofitInstance = null;
    private static PostsApi sPostsApi = null;

    private static final String BASE_URL = "https://www.bug.hr/";

    public static PostsApi getPostsApi() {
        if(sPostsApi == null) {
            sRetrofitInstance = getRetrofit();
            sPostsApi = sRetrofitInstance.create(PostsApi.class);
        }
        return sPostsApi;
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(createConverterFactory())
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .build();
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    private static Converter.Factory createConverterFactory() {
        return SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy()));
    }
}
