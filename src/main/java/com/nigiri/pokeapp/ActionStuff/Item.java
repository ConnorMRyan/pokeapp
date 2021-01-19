package com.nigiri.pokeapp.ActionStuff;
import com.nigiri.pokeapp.Models.Monster;


public abstract class Item extends BattleAction {

    @Override
    public abstract void execute(Monster user, Monster defender);

    @Override
    public abstract int getPriority();
}
