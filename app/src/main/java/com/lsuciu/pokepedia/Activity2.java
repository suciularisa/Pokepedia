package com.lsuciu.pokepedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lsuciu.pokepedia.data.Pokemon;


public class Activity2 extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Toolbar toolbar;
    private final String TAG = "Activity2";
    private Intent intent;
    private int colorId, color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(null);
        actionBar.setHomeAsUpIndicator(R.drawable.back_arrow);



        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);

        ViewPagerAdapterActivity2 adapter = new ViewPagerAdapterActivity2(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(adapter.getFragmentTitle(position));
                    }
                }).attach();

        intent = getIntent();
        Pokemon pokemon = (Pokemon)intent.getSerializableExtra("selected_pokemon");

        TextView type1 = findViewById(R.id.pokemon_type_1);
        TextView type2 = findViewById(R.id.pokemon_type_2);
        TextView name = findViewById(R.id.pokemon_name);

        type1.setText(pokemon.getTypes().get(0).getName());
        if(pokemon.getTypes().size() == 2) {
            type2.setText(pokemon.getTypes().get(1).getName());
            type2.setBackgroundResource(R.drawable.transparent_placeholder);
        }
        name.setText(pokemon.getName());

        LinearLayout layout = findViewById(R.id.top_layout);
        colorId = pokemon.getTypes().get(0).getColorId();
        color = ContextCompat.getColor(layout.getContext(), colorId);
        layout.getBackground().setTint(color);


        //change the status bar color
        Window window = this.getWindow();
        window.setStatusBarColor(color);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}