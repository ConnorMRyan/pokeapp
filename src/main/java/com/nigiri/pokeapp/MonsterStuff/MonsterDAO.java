package com.nigiri.pokeapp.MonsterStuff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Monster")
public class MonsterDAO {
    @Id
    @Column()
    private int ID;
    @Column()
    private String FAMILY_NAME;
    @Column
    private int TYPE_ONE; // com.nigiri.pokeapp.MonsterStuff.Monster's primary type
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

    public MonsterDAO() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFAMILY_NAME() {
        return FAMILY_NAME;
    }

    public void setFAMILY_NAME(String FAMILY_NAME) {
        this.FAMILY_NAME = FAMILY_NAME;
    }

    public int getTYPE_ONE() {
        return TYPE_ONE;
    }

    public void setTYPE_ONE(int TYPE_ONE) {
        this.TYPE_ONE = TYPE_ONE;
    }

    public int getTYPE_TWO() {
        return TYPE_TWO;
    }

    public void setTYPE_TWO(int TYPE_TWO) {
        this.TYPE_TWO = TYPE_TWO;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public int getSPAtk() {
        return SPAtk;
    }

    public void setSPAtk(int SPAtk) {
        this.SPAtk = SPAtk;
    }

    public int getSPDef() {
        return SPDef;
    }

    public void setSPDef(int SPDef) {
        this.SPDef = SPDef;
    }

    public int getSPD() {
        return SPD;
    }

    public void setSPD(int SPD) {
        this.SPD = SPD;
    }

    @Override
    public String toString() {
        return "MonsterDAO{" +
                "ID=" + ID +
                ", FAMILY_NAME='" + FAMILY_NAME + '\'' +
                ", TYPE_ONE=" + TYPE_ONE +
                ", TYPE_TWO=" + TYPE_TWO +
                ", HP=" + HP +
                ", ATK=" + ATK +
                ", DEF=" + DEF +
                ", SPAtk=" + SPAtk +
                ", SPDef=" + SPDef +
                ", SPD=" + SPD +
                '}';
    }
}
