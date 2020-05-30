package com.example.rickandmortyapi;

import  retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitGenerator {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Retrofit retrofitLoc = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static Character character;

    public static Character getCharacterService(){
        if (character == null){
            character = retrofit.create(Character.class);
        }
        return character;
    }
    private static Location location;

    public static Location getLocationService(){
        if (location == null){
            location = retrofitLoc.create(Location.class);
        }

        return location;
    }

}
