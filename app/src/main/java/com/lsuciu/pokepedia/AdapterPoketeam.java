package com.lsuciu.pokepedia;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


public class AdapterPoketeam extends RecyclerView.Adapter<AdapterPoketeam.ViewHolder>{

    Pokemon[] pokemons;
    private Context myContext;

    public AdapterPoketeam(Context context, Pokemon[] pokemons) {
        this.pokemons = pokemons;
        myContext = context;
    }

    @NonNull
    @Override
    public AdapterPoketeam.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemon_poketeam, parent, false);
        return new AdapterPoketeam.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPoketeam.ViewHolder holder, int position) {
        Pokemon pokemon = pokemons[position];
        holder.bind(pokemon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myContext, Activity2.class);
                myContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        int colorId;
        int color;
        private CardView cardView;
        private ImageView image_pokemon;
        private TextView pokemon_name;
        private TextView pokemon_type_1;
        private TextView pokemon_type_2;
        private TextView pokemon_id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_pokemon = itemView.findViewById(R.id.pokemon_image);
            pokemon_name = itemView.findViewById(R.id.pokemon_name);
            pokemon_type_1 = itemView.findViewById(R.id.pokemon_type1);
            pokemon_type_2 = itemView.findViewById(R.id.pokemon_type2);
            pokemon_id = itemView.findViewById(R.id.pokemon_id);
            cardView = itemView.findViewById(R.id.card_poketeam);
        }

        private void bind(Pokemon pokemon) {
            Glide.with(myContext)
                    .load("https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/other/cat_relaxing_on_patio_other/1800x1200_cat_relaxing_on_patio_other.jpg?resize=750px:*")
                    .into(image_pokemon);
            pokemon_name.setText(pokemon.getName());
            pokemon_type_1.setText(pokemon.getTypes().get(0).getName());
            if(pokemon.getTypes().size() == 2){
                pokemon_type_2.setText(pokemon.getTypes().get(1).getName());
                pokemon_type_2.setBackgroundResource(R.drawable.transparent_placeholder);
            }
            pokemon_id.setText(pokemon.getId());

            colorId = pokemon.getTypes().get(0).getColorId();
            color = ContextCompat.getColor(cardView.getContext(), colorId);
            cardView.setCardBackgroundColor(color);
        }
    }

}