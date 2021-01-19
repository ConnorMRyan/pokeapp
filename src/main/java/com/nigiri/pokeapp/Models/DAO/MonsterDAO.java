package com.nigiri.pokeapp.Models.DAO;

import lombok.*;
import me.sargunvohra.lib.pokekotlin.model.PokemonStat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "Monster")
public class MonsterDAO {
    @Id
    @Column
    private int ID; // Pokedex number of the pokemon.
    @Column
    private String FAMILY_NAME;
    @Column
    private int TYPE_ONE; // com.nigiri.pokeapp.Models.Monster's primary type
    @Column
    private int TYPE_TWO; // Optional secondary type
    @Column
    private int HP; // Amount of health
    @Column
    private int ATK; // Physical attacking power
    @Column
    private int DEF; // Physical defensive power
    @Column
    private int SPAtk; // Special attack
    @Column
    private int SPDef; // Special defense
    @Column
    private int SPD; // Speed

    public MonsterDAO(int ID, String FAMILY_NAME, int TYPE_ONE, int TYPE_TWO, int HP, int ATK, int DEF, int SPAtk, int SPDef, int SPD) {
        this.ID = ID;
        this.FAMILY_NAME = FAMILY_NAME;
        this.TYPE_ONE = TYPE_ONE;
        this.TYPE_TWO = TYPE_TWO;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.SPAtk = SPAtk;
        this.SPDef = SPDef;
        this.SPD = SPD;
    }

    public MonsterDAO(int ID, String FAMILY_NAME, int TYPE_ONE,int TYPE_TWO, ArrayList<PokemonStat> statList){
        this.ID = ID;
        this.FAMILY_NAME = FAMILY_NAME;
        this.TYPE_ONE = TYPE_ONE;
        this.TYPE_TWO = TYPE_TWO;
        this.HP = statList.get(0).getBaseStat();
        this.ATK = statList.get(1).getBaseStat();
        this.DEF = statList.get(2).getBaseStat();
        this.SPAtk = statList.get(3).getBaseStat();
        this.SPDef = statList.get(4).getBaseStat();
        this.SPD = statList.get(5).getBaseStat();

    }


    public MonsterDAO() { }


}
