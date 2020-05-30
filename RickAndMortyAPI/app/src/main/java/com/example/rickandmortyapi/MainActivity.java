package com.example.rickandmortyapi;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String TAG ="MainActivity";
    private RecyclerView recyclerViewCharacter;
    private CharacterAdapter characterAdapter;
    //private CharacterAdapterLocation characterAdapterLocation;
    private int page = 1;
    private boolean loading;
    private TextView textViewLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCharacter = findViewById(R.id.recyclerview_characters);
        characterAdapter = new CharacterAdapter(this);
        recyclerViewCharacter.setAdapter(characterAdapter);
        recyclerViewCharacter.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerViewCharacter.setLayoutManager(layoutManager);

        recyclerViewCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    int visibleItemCount=layoutManager.getChildCount();
                    int totalItemCount=layoutManager.getItemCount();
                    int pastItem=layoutManager.findFirstVisibleItemPosition();

                    if(loading){
                        if(visibleItemCount+pastItem>=totalItemCount){
                            loading=false;
                            page++;
                            cargarPersonajes(page);
                        }

                    }
                }
            }
        });

        loading=true;
        cargarPersonajes(page);

    }
    public void cargarPersonajes(int pages){

        RetrofitGenerator.getCharacterService().getCharacters(pages)
                .enqueue(new Callback<ResultCharacter>(){
                    @Override
                    public void onResponse(Call<ResultCharacter> call, Response<ResultCharacter> response) {
                        loading = true;
                        if (response.isSuccessful()){
                            if (response.body() != null){
                                characterAdapter.addResults(response.body().results);
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<ResultCharacter> call, Throwable t) {
                        loading=true;
                        Log.d(TAG,t.getMessage(),t);
                    }
                });

    }


}
