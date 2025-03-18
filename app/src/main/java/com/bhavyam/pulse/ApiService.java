package com.bhavyam.pulse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import java.util.List;
import retrofit2.http.Field;
public interface ApiService {
    @GET("data/all")  
    Call<List<NewsItem>> getNews();  

    @FormUrlEncoded  
    @POST("search")  
    Call<ApiResponse> searchNews(@Field("query") String query);
}