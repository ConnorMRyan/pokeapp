package com.nigiri.pokeapp.Models;

import com.nigiri.pokeapp.ActionStuff.Item;
import com.nigiri.pokeapp.Models.Monster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * A class that represents the team, stores the name and a list with each of the trainer's monsters.
 * The active monster is whatever monster is currently 'out'.
 */

@Data
public class Team {
    public String trainerName;
    ArrayList<Monster> teamList = new ArrayList<>();
    public Monster activeMonster;
    ArrayList<Item> itemList = new ArrayList<>();
    public Team(String trainerName) {
        this.trainerName = trainerName;
    }

    public void addMonster(Monster monster) {
        if (teamList.size() <= 6) {
            this.teamList.add(monster);
        } else {
            System.err.println("You can only have 6 pokemon");
        }
    }

    public void addItem(Item item){
        this.itemList.add(item);
    }

    public void useItem(Item item){
        itemList.remove(item);
    }

    void removeMonster(int ID) {
        teamList.remove(ID);
    }

    public void setActiveMonster() {
        for (Monster monster : teamList) {
            if (!monster.fainted) {
                activeMonster = monster;
            }
        }
    }

    public Monster getMonster(int id) {
        return teamList.get(id);
    }

    public boolean canContinue() {
        boolean canContinue = false;
        for (Monster monster : teamList) {
            canContinue = canContinue || !monster.fainted;
        }
        return canContinue;
    }

    public int numPokemon() {
        return teamList.size();
    }

}
