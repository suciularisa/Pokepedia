package com.lsuciu.pokepedia;

public enum  Type {


    NORMAL("Normal",R.color.normal), BUG("Bug",R.color.bug), FIGHTING("Fighting", R.color.fighting),
    GHOST("Ghost",R.color.ghost), ELECTRIC("Electric",R.color.electric), FLYING("Flying",R.color.flying),
    STEEL("Steel", R.color.steel), PSYCHIC("Psychic", R.color.psychic), POISON("Poison",R.color.poison),
    FIRE("Fire",R.color.fire), ICE("Ice",R.color.ice), GROUND("Ground",R.color.ground), WATER("Water",R.color.water),
    DRAGON("Dragon",R.color.dragon), ROCK("Rock",R.color.rock), GRASS("Grass",R.color.grass),
    DARK("Dark",R.color.dark), FAIRY("Fairy", R.color.fairy);

    private final String name;
    private final int colorId;

    Type(String name, int colorId) {
        this.name = name;
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public int getColorId() {
        return colorId;
    }
}
