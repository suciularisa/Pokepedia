package com.lsuciu.pokepedia;

import android.content.Context;
import android.content.Intent;
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
import com.lsuciu.pokepedia.data.Pokemon;
import com.lsuciu.pokepedia.data.PokemonDatabase;

import java.util.ArrayList;
import java.util.List;


public class AdapterPoketeam extends RecyclerView.Adapter<AdapterPoketeam.ViewHolder>{

    List<PokemonData> pokemons;
    private Context myContext;
    StringBuilder sb;
    Integer label, pokemonId;

    public AdapterPoketeam(Context context, List<PokemonData> pokemons, Integer label, Integer pokemonId) {
        this.pokemons = pokemons;
        myContext = context;
        this.label = label;
        this.pokemonId = pokemonId;
    }

    @NonNull
    @Override
    public AdapterPoketeam.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pokemon_poketeam, parent, false);
        return new AdapterPoketeam.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPoketeam.ViewHolder holder, int position) {
        PokemonData pokemon = pokemons.get(position);
        holder.bind(pokemon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pokemonId != pokemon.getId()){
                    Intent intent = new Intent(myContext, Activity2.class);
                    if(label == 2){
                        if(PokemonDatabase.getInstance(myContext).pokemonDao().getPokemon(pokemon.getId()) != null)
                            intent.putExtra("selected_pokemon2", pokemon);
                        else intent.putExtra("selected_pokemon", pokemon);
                    }
                    else intent.putExtra("selected_pokemon", pokemon);
                    myContext.startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(myContext, "Already on this pokemon...", Toast.LENGTH_SHORT);
                    toast.show();
                }

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
        int id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_pokemon = itemView.findViewById(R.id.pokemon_image);
            pokemon_name = itemView.findViewById(R.id.pokemon_name);
            pokemon_type_1 = itemView.findViewById(R.id.pokemon_type1);
            pokemon_type_2 = itemView.findViewById(R.id.pokemon_type2);
            pokemon_id = itemView.findViewById(R.id.pokemon_id);
            cardView = itemView.findViewById(R.id.card_poketeam);
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
            sb = new StringBuilder();
            id = pokemon.getId();

            if(id / 10 == 0){
                sb.append("#00" + id);
            }else if(id / 100 == 0){
                sb.append("#0" + id);
            }else sb.append("#" + id);
            pokemon_id.setText(sb.toString());


            colorId = pokemon.getTypes().get(0).getColorId();
            color = ContextCompat.getColor(cardView.getContext(), colorId);
            cardView.setCardBackgroundColor(color);
        }
    }

}
