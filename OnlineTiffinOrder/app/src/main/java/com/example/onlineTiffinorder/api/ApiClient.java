package com.example.onlineTiffinorder.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

  // public static final String BASE_URL = "http://www.adityapanchal.in/";
  //public static final String BASE_URL = "http://192.168.0.106/kj/";
  public static final String BASE_URL = "http://192.168.94.200/kj/";
//  public static final String BASE_URL = "http:192.168.117.200//:8080/kj/";
//  public static final String BASE_URL = "http://localhost/kj/";
//   public static final String BASE_URL = "http://kaushaljethava.me/kjapi/";

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientFile() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
