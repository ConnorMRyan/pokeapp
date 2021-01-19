package com.nigiri.pokeapp.ActionStuff;

import com.nigiri.pokeapp.Models.Monster;

public class HealAction extends BattleAction {
    int priority = 0;
    int amountHealed = 0;

    HealAction(int amountHealed) {
        this.amountHealed = amountHealed;
    }

    @Override
    public void execute(Monster user, Monster defender) {
        user.heal(amountHealed);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
