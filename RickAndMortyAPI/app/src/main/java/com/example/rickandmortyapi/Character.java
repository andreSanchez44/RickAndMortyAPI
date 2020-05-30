package com.example.rickandmortyapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Character {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/location/";
    @GET("character")
    Call<ResultCharacter> getCharacters(@Query("page") int page);



}
