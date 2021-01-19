package com.nigiri.pokeapp.Status;

import com.nigiri.pokeapp.Models.Monster;

import java.util.Random;

public class SleepStatus implements Status{
    private int numberTurns;
    SleepStatus(){
        Random random = new Random();
        numberTurns = random.nextInt(6) + 1;
    }

    @Override
    public void invoke(Monster monster) {

    }
}
