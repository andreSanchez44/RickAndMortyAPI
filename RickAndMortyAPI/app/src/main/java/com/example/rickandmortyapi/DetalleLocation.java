package com.example.rickandmortyapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleLocation extends AppCompatActivity {

    private TextView name,tipo,dimension;
    private final static String TAG ="DetalleLocation";
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion);

        name = findViewById(R.id.textViewName);
        tipo = findViewById(R.id.textViewTipo);
        dimension = findViewById(R.id.textViewDimension);

        Bundle bundle =this.getIntent().getExtras();
        if(bundle != null){
            url = bundle.getString("url");
            cargarLocations(url);

        }
    }



    public void cargarLocations(String url){
        RetrofitGenerator.getLocationService().getLocationByUrl(url).enqueue(new Callback<ResultLocation>() {
            @Override
            public void onResponse(Call<ResultLocation> call, Response<ResultLocation> response) {
                if (response.isSuccessful()){
                    if (response.body()!=null){
                        name.setText(response.body().name);
                        tipo.setText(response.body().type);
                        dimension.setText(response.body().dimension);
                    }
                }

            }

            @Override
            public void onFailure(Call<ResultLocation> call, Throwable t) {
                Log.e(TAG,t.getMessage(),t);

            }
        });

    }
}
