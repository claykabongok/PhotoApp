package com.claykab.photoApp.network;


import com.claykab.photoApp.model.PictureResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Picture_API_Interface {


    @GET("api/")
    Call<PictureResponse> getPictures(@Query("key") String key,  @Query("image_type") String image_type, @Query("per_page") int per_page, @Query("safesearch") boolean safesearch, @Query("editors_choice") boolean editors_choice);

    @GET("api/")
    Call<PictureResponse> getPicturesCat(@Query("key") String key, @Query("category") String category, @Query("image_type") String image_type, @Query("per_page") int per_page, @Query("safesearch") boolean safesearch);

    @GET("api/")
    Call<PictureResponse> searchPictures(@Query("key") String key,  @Query("image_type") String image_type, @Query("per_page") int per_page, @Query("safesearch") boolean safesearch, @Query("q") String query);


    @GET("api/")
    Call<PictureResponse> getPicturesById(@Query("key") String key, @Query("id") long id);




}
