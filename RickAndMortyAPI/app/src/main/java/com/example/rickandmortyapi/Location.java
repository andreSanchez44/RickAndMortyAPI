package com.example.rickandmortyapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Location {
    @GET
    Call<ResultLocation> getLocationByUrl(@Url String url);
}
