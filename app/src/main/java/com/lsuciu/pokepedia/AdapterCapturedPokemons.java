package com.lsuciu.pokepedia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.lsuciu.pokepedia.data.CapturedPokemon;
import com.lsuciu.pokepedia.data.Pokemon;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdapterCapturedPokemons extends RecyclerView.Adapter<AdapterCapturedPokemons.ViewHolder>{

    private Context mContext;
    private List<CapturedPokemon> pokemonDataList;

    public AdapterCapturedPokemons(Context context, List<CapturedPokemon> pokemons) {
        this.pokemonDataList = pokemons;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_captured_pokemon, parent, false);
        return new AdapterCapturedPokemons.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterCapturedPokemons.ViewHolder holder, int position) {
        CapturedPokemon pokemon = pokemonDataList.get(position);
        holder.bind(pokemon);

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Activity2.class);
                intent.putExtra("selected_pokemon2", pokemon);
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return pokemonDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        int colorId;
        int color;
        private CardView cardView;
        private ImageView image_pokemon;
        private TextView pokemon_name;
        private TextView pokemon_type_1;
        private TextView pokemon_type_2;
        private TextView pokemon_id;
        private ImageButton pokemon_location;
        private TextView pokemon_date;
        int id;
        StringBuilder sb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_pokemon = itemView.findViewById(R.id.pokemon_image);
            pokemon_name = itemView.findViewById(R.id.pokemon_name);
            pokemon_type_1 = itemView.findViewById(R.id.pokemon_type1);
            pokemon_type_2 = itemView.findViewById(R.id.pokemon_type2);
            pokemon_id = itemView.findViewById(R.id.pokemon_id);
            cardView = itemView.findViewById(R.id.card_poketeam);
            pokemon_location = itemView.findViewById(R.id.location_capture);
            pokemon_date = itemView.findViewById(R.id.date_capture);

        }

        private void bind(CapturedPokemon pokemon){
            Glide.with(mContext)
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

            pokemon_date.setText(pokemon.getDateFormated());

            pokemon_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    MapDialog mapDialog = MapDialog.getInstance();
                    mapDialog.setCancelable(false);
                    mapDialog.setPokemon(pokemon);
                    mapDialog.show(manager, null);
                }
            });
        }


    }
}
