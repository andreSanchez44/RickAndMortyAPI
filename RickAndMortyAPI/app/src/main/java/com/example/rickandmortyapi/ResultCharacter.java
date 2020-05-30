package com.example.rickandmortyapi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResultCharacter {

        static class Info{
            public int count;
            public int pages;
            public String next;
            public String prev;
        }
        public Info info;

        static class Result{
            public int id;
            public String name;
            public String status;
            public String species;
            public String type;
            public String gender;
            public String image;
            public Location location;
        }

        public List<Result> results;

        static class Location{
            public String name;
            public String url;

            public String getNameL() {
                return name;
            }

            public void setNameL(String nameL) {
                this.name = nameL;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }


}
