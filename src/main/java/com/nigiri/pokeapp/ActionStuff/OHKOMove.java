package com.nigiri.pokeapp.ActionStuff;

import com.nigiri.pokeapp.MonsterStuff.Monster;


public class OHKOMove extends MoveBase{


    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void execute(Monster user, Monster defender) {

    }

    @Override
    public String printMove() {
        return null;
    }

    @Override
    public int estimateDamage(Monster user, Monster defender) {
        return (int)(accuracy/100) * defender.getCurrentHp();
    }
}
