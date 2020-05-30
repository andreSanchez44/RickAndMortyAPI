package com.example.rickandmortyapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<ResultCharacter.Result> results = new ArrayList<>();
    private Context context;
    static ProgressDialog progressDialog = null;


    public void addResults(List<ResultCharacter.Result> results){
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    public CharacterAdapter(Context context){
        results=new ArrayList<>();
        this.context=context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View card = inflater.inflate(R.layout.character_card, parent, false);
        return new CharacterViewHolder(card);
    }
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        ResultCharacter.Result result = results.get(position);
        holder.bind(result);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(ResultCharacter.Result result){
            ImageView imageViewProfile = itemView.findViewById(R.id.imageview_profile);
            TextView textViewName, textViewGender, textViewStatus, textViewSpecies, textViewType,textViewLocation;
            textViewName = itemView.findViewById(R.id.textview_name);
            textViewGender = itemView.findViewById(R.id.textView_gender);
            textViewSpecies = itemView.findViewById(R.id.textView_species);
            textViewStatus = itemView.findViewById(R.id.textView_status);
            textViewType = itemView.findViewById(R.id.textView_type);
            textViewLocation = itemView.findViewById(R.id.textView_location);


            textViewName.setText(result.name);
            textViewGender.setText(result.gender);
            textViewSpecies.setText(result.species);
            textViewStatus.setText(result.status);
            textViewType.setText(result.type);
            textViewLocation.setText(result.location.name);

            textViewLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleLocation.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url",result.location.url);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });

            if(result.type.equals("")){
                textViewType.setVisibility(View.GONE);
            }

            Glide.with(itemView)
                    .load(result.image)
                    .into(imageViewProfile);

        }
    }
}
