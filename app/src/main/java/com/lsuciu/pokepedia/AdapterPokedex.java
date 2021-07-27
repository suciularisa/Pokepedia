package com.lsuciu.pokepedia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lsuciu.pokepedia.data.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class AdapterPokedex extends RecyclerView.Adapter<AdapterPokedex.ViewHolder>{

    List<PokemonData> pokemons;
    private Context myContext;

    public AdapterPokedex(Context context, List<PokemonData> pokemons) {
        this.pokemons = pokemons;
        myContext = context;
    }

    @NonNull
    @Override
    public AdapterPokedex.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemon_pokedex, parent, false);
        return new AdapterPokedex.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPokedex.ViewHolder holder, int position) {
        PokemonData pokemon = pokemons.get(position);
        holder.bind(pokemon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open second activity
                Intent i = new Intent(myContext, Activity2.class);
                myContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
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
            cardView = itemView.findViewById(R.id.card_pokedex);
        }

        private void bind(PokemonData pokemon) {
            Glide.with(myContext)
                    .load(pokemon.getImage())
                    .into(image_pokemon);
            pokemon_name.setText(pokemon.getName());
            pokemon_type_1.setText(pokemon.getTypes().get(0).getName());
            if(pokemon.getTypes().size() == 2){
                pokemon_type_2.setText(pokemon.getTypes().get(1).getName());
                pokemon_type_2.setBackgroundResource(R.drawable.transparent_placeholder);
            }
            pokemon_id.setText(Integer.toString(pokemon.getId()));

            colorId = pokemon.getTypes().get(0).getColorId();
            color = ContextCompat.getColor(cardView.getContext(), colorId);
            cardView.setCardBackgroundColor(color);
        }
    }


}

