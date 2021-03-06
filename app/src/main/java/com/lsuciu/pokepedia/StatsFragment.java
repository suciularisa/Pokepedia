package com.lsuciu.pokepedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.lsuciu.pokepedia.data.Pokemon;
import com.lsuciu.pokepedia.data.PokemonDao;
import com.lsuciu.pokepedia.data.PokemonDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class StatsFragment extends Fragment{

    public static final String title = "STATS";
    PokemonDetails pokemon;
    LinearProgressIndicator progress_hp;
    LinearProgressIndicator progress_attack;
    LinearProgressIndicator progress_defense;
    LinearProgressIndicator progress_specialAttack;
    LinearProgressIndicator progress_specialDefense;
    LinearProgressIndicator progress_speed;
    TextView hp;
    TextView attack;
    TextView defense;
    TextView specialAttack;
    TextView specialDefense;
    TextView speed;
    TextView total;
    private PageViewModel pageViewModel;

    public StatsFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        progress_hp = view.findViewById(R.id.progress_hp);
        progress_attack = view.findViewById(R.id.progress_attack);
        progress_defense = view.findViewById(R.id.progress_defense);
        progress_specialAttack = view.findViewById(R.id.progress_special_attack);
        progress_specialDefense = view.findViewById(R.id.progress_special_defense);
        progress_speed = view.findViewById(R.id.progress_speed);

        hp = view.findViewById(R.id.tag_hp);
        attack = view.findViewById(R.id.tag_attack);
        defense = view.findViewById(R.id.tag_defense);
        specialAttack = view.findViewById(R.id.tag_special_attack);
        specialDefense = view.findViewById(R.id.tag_special_defense);
        speed = view.findViewById(R.id.tag_speed);

        total = view.findViewById(R.id.total_stats);

        progress_hp.setMax(255);
        progress_attack.setMax(190);
        progress_defense.setMax(230);
        progress_specialAttack.setMax(194);
        progress_specialDefense.setMax(230);
        progress_speed.setMax(200);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageViewModel.getPokemon().observe(requireActivity(), new Observer<PokemonDetails>() {
            @Override
            public void onChanged(PokemonDetails pokemonDetails) {
                updateData(pokemonDetails);
            }
        });
    }


     private void updateData(PokemonDetails pokemonDetails) {
        this.pokemon = pokemonDetails;

        Log.d("test2", String.valueOf(pokemon.getName()));

        int hpValue = pokemon.getStats().get(0);
        int attackValue = pokemon.getStats().get(1);
        int defenseValue = pokemon.getStats().get(2);
        int spAttackValue = pokemon.getStats().get(3);
        int spDefenseValue = pokemon.getStats().get(4);
        int speedValue = pokemon.getStats().get(5);
        int totalvalue = hpValue + attackValue + defenseValue + spAttackValue + spDefenseValue + speedValue;

        hp.setText(Integer.toString(hpValue));
        attack.setText(Integer.toString(attackValue));
        defense.setText(Integer.toString(defenseValue));
        specialAttack.setText(Integer.toString(spAttackValue));
        specialDefense.setText(Integer.toString(spDefenseValue));
        speed.setText(Integer.toString(speedValue));

        progress_hp.setProgress(hpValue);
        progress_attack.setProgress(attackValue);
        progress_defense.setProgress(defenseValue);
        progress_specialAttack.setProgress(spAttackValue);
        progress_specialDefense.setProgress(spDefenseValue);
        progress_speed.setProgress(speedValue);

        total.setText(Integer.toString(totalvalue));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}