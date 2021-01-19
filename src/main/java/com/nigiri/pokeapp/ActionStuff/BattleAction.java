package com.nigiri.pokeapp.ActionStuff;

import com.nigiri.pokeapp.Models.Monster;

/**
 * The highest level base class for battle actions(Moves, Items, Switching.) All must have the ability to be executed and a priority.
 */

public abstract class BattleAction {
    int priority;

    public abstract void execute(Monster user, Monster defender);

    public abstract int getPriority();
}
