package com.example.edulearn.api;

import com.example.edulearn.utils.TokenManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/"; // Use your actual API URL (10.0.2.2 points to localhost when using emulator)
    private static Retrofit retrofit = null;
    private static TokenManager tokenManager;

    public static void initialize(TokenManager tokenManager) {
        ApiClient.tokenManager = tokenManager;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        
                        // Add auth token to requests if available
                        if (tokenManager != null && tokenManager.getToken() != null) {
                            Request.Builder requestBuilder = original.newBuilder()
                                    .header("Authorization", "Bearer " + tokenManager.getToken())
                                    .method(original.method(), original.body());
                            
                            return chain.proceed(requestBuilder.build());
                        }
                        
                        return chain.proceed(original);
                    })
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
