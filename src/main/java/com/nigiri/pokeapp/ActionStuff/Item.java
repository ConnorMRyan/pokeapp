package com.nigiri.pokeapp.ActionStuff;
import com.nigiri.pokeapp.MonsterStuff.*;


public class Item extends BattleAction {

    @Override
    public void execute(Monster user, Monster defender) {
    System.out.println("blah");
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
