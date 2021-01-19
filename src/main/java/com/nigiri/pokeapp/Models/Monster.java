package com.nigiri.pokeapp.Models;

import com.nigiri.pokeapp.ActionStuff.MoveBase;
import com.nigiri.pokeapp.Models.DAO.MonsterDAO;
import com.nigiri.pokeapp.Status.NormalStatus;
import com.nigiri.pokeapp.Status.Status;
import lombok.*;

import java.util.ArrayList;

@Data
public class Monster {
  private final String NAME;
  private final String FAMILY_NAME;
  private final int TYPE_ONE; // com.nigiri.pokeapp.Models.Monster's primary type
  private final int TYPE_TWO; // Optional secondary type
  private final int LEVEL;
  private final int TOTAL_HP; // Amount of health
  private int currentHp; // Amount of current health
  private final int ATK; // Physical attacking power
  private int atkMod = 0;
  private final int DEF; // Physical defensive power
  private int defMod = 0;
  private final int SPC; // Special attack and defense
  private int spcMod = 0;
  private final int SPD; // Speed
  private int spdMod = 0;
  private final int id;
  //private final Status status = new NormalStatus();
  public boolean fainted;
  private final ArrayList<MoveBase> movesList = new ArrayList<>();

  /**
   * A constructor for monsters with a nickname.
   */
  public Monster(
          int HP,
          int ATK,
          int DEF,
          int SPC,
          int SPD,
          int TYPE_ONE,
          int TYPE_TWO,
          int LEVEL,
          String NICK_NAME,
          String FAMILY_NAME, int id) {
    //status = new com.nigiri.pokeapp.Status.Status();
    this.LEVEL = LEVEL;
    this.id = id;
    this.TOTAL_HP = convertBaseStat(HP) + 5 + LEVEL;
    this.currentHp = convertBaseStat(HP) + 5 + LEVEL;
    this.ATK = convertBaseStat(ATK);
    this.DEF = convertBaseStat(DEF);
    this.SPC = convertBaseStat(SPC);
    this.SPD = convertBaseStat(SPD);
    this.TYPE_ONE = TYPE_ONE;
    this.TYPE_TWO = TYPE_TWO;
    this.NAME = NICK_NAME;
    this.FAMILY_NAME = FAMILY_NAME;
  }

  public Monster(MonsterDAO monsterDAO, int level, String nick ){
    this.LEVEL = level;
    this.id = monsterDAO.getID();
    this.TOTAL_HP = convertBaseStat(monsterDAO.getHP()) + 5 + LEVEL;
    this.currentHp = convertBaseStat(monsterDAO.getHP()) + 5 + LEVEL;
    this.ATK = convertBaseStat(monsterDAO.getATK());
    this.DEF = convertBaseStat(monsterDAO.getDEF());
    this.SPC = convertBaseStat(monsterDAO.getSPAtk());
    this.SPD = convertBaseStat(monsterDAO.getSPD());
    this.TYPE_ONE = monsterDAO.getTYPE_ONE();
    this.TYPE_TWO = monsterDAO.getTYPE_TWO();
    this.FAMILY_NAME = monsterDAO.getFAMILY_NAME();
    this.NAME = nick;
  }

  /**
   * This is a constructer for when monsters don't have a nickname, in retrospect I probably could
   * have combined these together and taken care of the nickname during creation.
   */
  public Monster(
          int HP,
          int ATK,
          int DEF,
          int SPC,
          int SPD,
          int TYPE_ONE,
          int TYPE_TWO,
          int LEVEL,
          String FAMILY_NAME, int id) {
    //status = new com.nigiri.pokeapp.Status.Status();
    this.LEVEL = LEVEL;
    this.id = id;
    this.TOTAL_HP = convertBaseStat(HP) + 5 + LEVEL;
    this.currentHp = convertBaseStat(HP) + 5 + LEVEL;
    this.ATK = convertBaseStat(ATK);
    this.DEF = convertBaseStat(DEF);
    this.SPC = convertBaseStat(SPC);
    this.SPD = convertBaseStat(SPD);
    this.TYPE_ONE = TYPE_ONE;
    this.TYPE_TWO = TYPE_TWO;
    this.NAME = FAMILY_NAME;
    this.FAMILY_NAME = FAMILY_NAME;
  }


    /**
   * Returns a modified stat based on the stat and a modifier from -6 to +6,
   *
   * @param statBase
   * @param statMod
   * @return
   */
  public int getStat(int statBase, int statMod) {
    switch (statMod) {
      case -6:
        return (statBase * 25) / 100;
      case -5:
        return (statBase * 28) / 100;
      case -4:
        return (statBase * 33) / 100;
      case -3:
        return (statBase * 40) / 100;
      case -2:
        return (statBase * 50) / 100;
      case -1:
        return (statBase * 66) / 100;

      case 1:
        return (statBase * 150) / 100;
      case 2:
        return (statBase * 200) / 100;
      case 3:
        return (statBase * 250) / 100;
      case 4:
        return (statBase * 300) / 100;
      case 5:
        return (statBase * 350) / 100;
      case 6:
        return (statBase * 400) / 100;
      default:
        return statBase;
    }
  }

  public void heal(int amountHeal) {
    this.currentHp = +amountHeal;

    if (currentHp > TOTAL_HP) {
      this.currentHp = TOTAL_HP;
    }

  }

  public void resetStatMods() {
    this.atkMod = 0;
    this.defMod = 0;
    this.spdMod = 0;
    this.spcMod = 0;
  }

  public void addMove(MoveBase pokeMove) {
    movesList.add(pokeMove);
  }

  public MoveBase getMove(int ID) {
    try {
      return movesList.get(ID);
    } catch (Exception e) {
      return movesList.get(0);
    }
  }

  public void printMoves() {
    for (MoveBase pokeMove : movesList) {
      System.out.println(pokeMove);
    }
  }

  public void takeDamage(int damage) {
    currentHp = currentHp - damage;
    if (currentHp <= 0) {
      currentHp = 0;
      fainted = true;
    }
  }

  /**
   * Returns the correct base stats, currently assumes maximum IV and EV values, but may allow for
   * changed in the future.
   *
   * @param stat
   * @return
   */
  private int convertBaseStat(int stat) {
    return (((((stat + 15) * 2) + 63) * this.LEVEL) / 100) + 5;
  }

}
