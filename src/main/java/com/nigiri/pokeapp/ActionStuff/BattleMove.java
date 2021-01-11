package com.nigiri.pokeapp.ActionStuff;

import com.nigiri.pokeapp.MonsterStuff.Monster;
import com.nigiri.pokeapp.Utils.types;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Move;
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * The default battle move, It uses a users stats to deal damage to an opponent.
 */

public class BattleMove extends MoveBase {
  int power;


  /**
   * Constructs a move based on a moveString, which the class can write with the print move method.
   *
   * @param moveString
   */

  public BattleMove(String moveString) {
    Scanner in = new Scanner(moveString);
    in.useDelimiter(",");
    name = in.next();
    this.power = in.nextInt();
    this.accuracy = in.nextInt();
    this.type = in.nextInt();
    this.PP = in.nextInt();
    this.maxPP = this.PP;
  }

  /**
   * Constructs a move using the pokeAPI and the ID of a move. This should be moved up to the parent level,
   * because now you can implicitly call a battle move constructor on any move type. It won't break, but it will
   * make a default move.
   * @param moveID
   */

  public BattleMove(int moveID) {
    PokeApi pokeApi = new PokeApiClient();
    Move move = pokeApi.getMove(moveID);
    if (move.getPower() != null) {
      this.power = move.getPower();
    } else {
      System.err.println("The moves power is bugged. it has been set to 50");
      this.power = 50;
    }
    if (move.getAccuracy() != null) {
      this.accuracy = move.getAccuracy();
    } else {
      System.err.println("The moves accuracy is bugged. it has been set to 80");
      this.power = 80;
    }
    NamedApiResource type = move.getType();
    this.type = types.getType(type.getName());
    if (move.getPp() != null) {
      this.PP = move.getPp();
    } else {
      System.err.println("The moves PP is bugged. It has been set to 15");
      this.PP = 15;
    }
    name = move.getName();
  }

  BattleMove(int power, int accuracy, int type, int PP, String moveName) {
    this.power = power;
    this.accuracy = accuracy;
    name = moveName;
    this.type = type;
    this.PP = PP;
    this.maxPP = PP;
  }


  /**
   * Uses a special attack if the moove is from a special type, or a physical move if from a phsyical type.
   * calculates if the move is a crit, need to implement accuracy.
   *
   * @param user
   * @param target
   */
  @Override
  public void execute(Monster user, Monster target) {
    if (isAHit()) {
      double modifier = getModifier(user, target);
      int crit = 1;
      boolean isCrit = isACrit(user);
      if (isCrit) {
        crit = 2;
      }
      if (IntStream.of(types.physical).anyMatch(x -> x == type)) {
        int damage =
                (int)
                        Math.round(
                                ((((((2 * user.getLEVEL() * crit) / 5.) + 2)
                                        * this.getPower()
                                        * (user.getATK() / (double) target.getDEF()))
                                        / 50
                                        + 2)
                                        * modifier));
        System.out.print(
                user.getNAME()
                        + " did "
                        + damage
                        + " to "
                        + target.getNAME()
                        + " with "
                        + name
                        + " it was "
                        + superEffective(type, target));
        if (isCrit) {
          System.out.print(" and it was a crit" + "\n");
        }

        target.takeDamage(damage);
      }
      if (IntStream.of(types.special).anyMatch(x -> x == type)) {
        int damage =
                (int)
                        Math.round(
                                ((((((2 * user.getLEVEL() * crit) / 5.) + 2)
                                        * this.getPower()
                                        * (user.getSPC() / (double) target.getSPC()))
                                        / 50
                                        + 2)
                                        * modifier));
        System.out.print(
                user.getNAME()
                        + " did "
                        + damage
                        + " to "
                        + target.getNAME()
                        + " with "
                        + name
                        + " it was "
                        + superEffective(type, target));
        if (isCrit) {
          System.out.print(" and it was a crit");
        }
        target.takeDamage(damage);
      }
      System.out.println();
    } else {
      System.out.println("Sorry, " + name + " missed.");
    }
    usePP();

  }

  public int estimateDamage(Monster user, Monster target) {
    return (int) Math.round(((((((2 * user.getLEVEL()) / 5.) + 2)
            * this.getPower()
            * (user.getSPC() / target.getSPC())) / 50 + 2)
            * types.TYPE_EFFECTIVNESS[type][target.getType1()] * types.TYPE_EFFECTIVNESS[type][target.getType2()]));
  }

  /**
   * Calculates the damage modifier based on RNG, typing and if STAB is applied.
   *
   * @param user
   * @param defender
   * @return
   */

  private double getModifier(Monster user, Monster defender) {
    Random random = new Random();
    int val = random.nextInt(38);
    double STAB = 1;
    if (user.getType1() == type || user.getType2() == type) {
      STAB = 1.5;
    }
    return ((217 + val) / 255.)
            * STAB
            * types.TYPE_EFFECTIVNESS[type][defender.getType1()]
            * types.TYPE_EFFECTIVNESS[type][defender.getType2()];
  }

  private boolean isAHit() {
    Random rand = new Random();
    int val = rand.nextInt(256);
    int target = (accuracy * 256) / 100;
    return val < target;
  }

  private boolean isACrit(Monster user) {
    int tVal = user.getSPD() / 2;
    Random random = new Random();
    int target = random.nextInt(256);
    int critNum = Math.min(255, tVal);
    return target < critNum;
  }

  @Override
  public String toString() {
    return name;
  }

  public String fancyString() {
    return "Move{"
            + "power="
            + power
            + ", accuracy="
            + accuracy
            + ", moveName='"
            + name
            + '\''
            + ", type="
            + types.typeName(type)
            + +'}';
  }

  public int getAccuracy() {
    return accuracy;
  }

  public int getPower() {
    return power;
  }

  public void usePP() {
    PP = PP - 1;
  }

  public int getPP() {
    return PP;
  }

  public int getMaxPP() {
    return maxPP;
  }

  public int getType() {
    return type;
  }

  public String getMoveName() {
    return name;
  }

  @Override
  public int getPriority() {
    return priority;
  }

  public String printMove() {
    return "0 \n"
            + this.name
            + ","
            + this.power
            + ","
            + this.accuracy
            + ","
            + this.type
            + ","
            + this.PP
            + ","
            + "\n";
  }

  static String superEffective(int typeOne, Monster def) {
    double effectiveness = types.TYPE_EFFECTIVNESS[typeOne][def.getType1()] * types.TYPE_EFFECTIVNESS[typeOne][def.getType2()];
    if (effectiveness == 0) {
      return "ineffective";
    } else if (effectiveness == .5) {
      return "not very effective";
    } else if (effectiveness == 1) {
      return "normally effective";
    } else if (effectiveness >= 2) {
      return "super effective!";
    } else {
      return "something broke";
    }
}
}
