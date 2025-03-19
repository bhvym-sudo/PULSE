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
    Call<List<NewsItem>> getWorldNews();
    @GET("data/europe")
    Call<List<NewsItem>> getEuropeNews();

    @GET("data/africa")
    Call<List<NewsItem>> getAfricaNews();

    @GET("data/americas")
    Call<List<NewsItem>> getAmericasNews();

    @GET("data/asiapac")
    Call<List<NewsItem>> getAsiaPacificNews();

    @GET("data/middleeast")
    Call<List<NewsItem>> getMiddleEastNews();

    @FormUrlEncoded  
    @POST("search")  
    Call<ApiResponse> searchNews(@Field("query") String query);
}