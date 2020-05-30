package com.example.rickandmortyapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultLocation {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("type")
    @Expose
    public String type;


    @SerializedName("dimension")
    @Expose
    public String dimension;


}
