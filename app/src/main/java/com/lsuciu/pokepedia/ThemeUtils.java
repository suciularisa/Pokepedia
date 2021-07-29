package com.lsuciu.pokepedia;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ThemeUtils {

    private final static Map<String, Integer> THEMES = new HashMap<String, Integer>(){
        {
            put(Type.NORMAL.getName(), R.style.NormalTheme);
            put(Type.BUG.getName(), R.style.BugTheme);
            put(Type.FIGHTING.getName(), R.style.FightingTheme);
            put(Type.GHOST.getName(), R.style.GhostTheme);
            put(Type.ELECTRIC.getName(), R.style.ElectricTheme);
            put(Type.FLYING.getName(), R.style.FightingTheme);
            put(Type.STEEL.getName(), R.style.SteelTheme);
            put(Type.PSYCHIC.getName(), R.style.PsychicTheme);
            put(Type.POISON.getName(), R.style.PoisonTheme);
            put(Type.FIRE.getName(), R.style.FireTheme);
            put(Type.ICE.getName(), R.style.IceTheme);
            put(Type.GROUND.getName(), R.style.GroundTheme);
            put(Type.WATER.getName(), R.style.WaterTheme);
            put(Type.DRAGON.getName(), R.style.DragonTheme);
            put(Type.ROCK.getName(), R.style.RockTheme);
            put(Type.GRASS.getName(), R.style.GrassTheme);
            put(Type.DARK.getName(), R.style.DarkTheme);
            put(Type.FAIRY.getName(), R.style.FairyTheme);
        }
    };

    public ThemeUtils() {
    }

    public static Integer getThemeId(String type){
        return THEMES.get(type);
    }
}
