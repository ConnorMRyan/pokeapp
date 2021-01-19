package com.nigiri.pokeapp.BattleStuff;



import com.nigiri.pokeapp.ActionStuff.BattleAction;
import com.nigiri.pokeapp.ActionStuff.MoveBase;
import com.nigiri.pokeapp.ActionStuff.SwitchMonster;
import com.nigiri.pokeapp.Models.Team;
import com.nigiri.pokeapp.Models.Monster;
import java.util.Scanner;

public class Battle {
  final int BATTLE_MENU_MAX = 3;
  Team teamOne;
  Team teamTwo;
  Team activeTeam;
  Scanner in = new Scanner(System.in);
  int activeID = 1;
  Team inactiveTeam;

  public Battle(Team teamOne, Team teamTwo) {
    this.teamOne = teamOne;
    this.activeTeam = teamOne;
    this.teamTwo = teamTwo;
    this.inactiveTeam = teamTwo;
    teamOne.setActiveMonster();
    teamTwo.setActiveMonster();
    while (checkLost()) {
      BattleAction playerOne = battleTurn();
      switchActive();
      BattleAction playerTwo = battleTurn();
      switchActive();
      displayHP(activeTeam.getActiveMonster());
      displayHP(inactiveTeam.getActiveMonster());
      if (playerOne.getPriority() >= playerTwo.getPriority()) {
        playerOne.execute(activeTeam.getActiveMonster(), inactiveTeam.getActiveMonster());
        switchActive();
        playerTwo.execute(activeTeam.getActiveMonster(), inactiveTeam.getActiveMonster());
        switchActive();
      }

    }
    if (teamOne.canContinue()) {
      System.out.println(teamOne.trainerName + " Won!");
    }
    if (teamTwo.canContinue()) {
      System.out.println(teamTwo.trainerName + "Won!");
    }
  }

  public Battle(Team teamOne, Team teamTwo, Boolean multiplayer) {
    this.teamOne = teamOne;
    this.activeTeam = teamOne;
    this.teamTwo = teamTwo;
    this.inactiveTeam = teamTwo;
    teamOne.setActiveMonster();
    teamTwo.setActiveMonster();
    while (checkLost()) {
      if (activeTeam.activeMonster.fainted) {
        activeTeam.setActiveMonster();
      }
      BattleAction ba = battleTurn();

      MoveBase moveToUse = (MoveBase) compTurn();
      if (ba.getPriority() >= moveToUse.getPriority()) {
        ba.execute(activeTeam.activeMonster, inactiveTeam.activeMonster);
        if (!inactiveTeam.activeMonster.fainted) {
          moveToUse.execute(inactiveTeam.activeMonster, activeTeam.activeMonster);
        } else {
          inactiveTeam.setActiveMonster();
        }
        displayHP(teamOne.activeMonster);
        displayHP(teamTwo.activeMonster);
      } else {
        moveToUse.execute(inactiveTeam.activeMonster, activeTeam.activeMonster);
        if (!activeTeam.activeMonster.fainted) {
          ba.execute(activeTeam.activeMonster, inactiveTeam.activeMonster);
        } else {
          activeTeam.setActiveMonster();
        }
        displayHP(teamOne.activeMonster);
        displayHP(teamTwo.activeMonster);

      }

    }
    if (teamOne.canContinue()) {
      System.out.println(teamOne.trainerName + " Won!");
    }
    if (teamTwo.canContinue()) {
      System.out.println(teamTwo.trainerName + "Won!");
    }


  }

  boolean checkLost() {
    // check if both teams can continue, if they can the battle hasn't been won yet.
    return (teamOne.canContinue() && teamTwo.canContinue());
  }

  BattleAction compTurn() {
    BattleAction tenative = inactiveTeam.activeMonster.getMove(0);
    int oldDmg = 0;
    for (MoveBase move : inactiveTeam.activeMonster.getMovesList()) {
      if (move.estimateDamage(inactiveTeam.activeMonster, activeTeam.activeMonster) > oldDmg) {
        tenative = move;
      }
    }
    return tenative;

  }

  SwitchMonster changePokemon(Team team) {
    System.out.println("Which pokemon would you like to switch to?");
    for (int i = 0; i < team.numPokemon(); i++) {
      System.out.println("" + (i + 1) + ":- " + team.getMonster(i));
    }
    int num = in.nextInt() - 1;
    return new SwitchMonster(team, num);
  }

  void printBattleMenu() {
    System.out.println("-_-_-PokeMav.com.nigiri.pokeapp.BattleStuff.Battle Menu-_-_-");
    System.out.println("1: Attack");
    System.out.println("2: Use item");
    System.out.println("3: Switch Pokemon");
    System.out.println("----------------------");
  }

  void displayHP(Monster monster) {
    System.out.println("--" + monster.getNAME() + "--");
    System.out.println(
            ""
                    + monster.getCurrentHp()
                    + " HP out of "
                    + monster.getTOTAL_HP()
                    + "HP");
    System.out.println("-------------------");

  }

  void displayAttacks() {
    System.out.println("----" + activeTeam.activeMonster.getNAME() + "----");
    System.out.println("------Moves-----");
    for (int i = 0; i < activeTeam.activeMonster.getMovesList().size(); i++) {
      System.out.println("" + (i + 1) + "- " + activeTeam.activeMonster.getMove(i) + " Has " + activeTeam.activeMonster.getMove(i).getPP() + " PP out of " + activeTeam.activeMonster.getMove(i).getMaxPP());
    }
    System.out.println("----------------");
  }

  void switchActive() {
    if (activeID == 1) {
      activeID = 2;
      activeTeam = teamTwo;
      inactiveTeam = teamOne;
    } else {
      activeID = 1;
      activeTeam = teamOne;
      inactiveTeam = teamTwo;
    }
  }

  //int getMenuItem(int menuMax) {
  // if (menuItem > 0 && menuItem < menuMax) {
  // return menuItem - 1;
  // }
  // System.out.println("Sorry, not a valid input");
  // return getMenuItem(menuMax);
  // }

  BattleAction battleTurn() {
    printBattleMenu();
    int choice = in.nextInt() - 1;
    if (choice == 0) {
      displayAttacks();
      return getMove();
    }
    if (choice == 1) {
      // todo implement items, if time allows.
      System.out.println("tried new item healed 20");
      return new BattleAction() {
        @Override
        public void execute(Monster user, Monster defender) {
          user.heal(20);
        }

        @Override
        public int getPriority() {
          return 0;
        }
      };
    }
    if (choice == 2) {
      return changePokemon(activeTeam);
    }
    return null;
  }

  void displayActivePokemon() {
  }

  MoveBase getMove() {
    int moveNum = in.nextInt() - 1;
    return activeTeam
            .activeMonster
            .getMove(moveNum);

  }

  public Team getTeamOne() {
    return teamOne;
  }

  public void setTeamOne(Team teamOne) {
    this.teamOne = teamOne;
  }

  public Team getTeamTwo() {
    return teamTwo;
  }

  public void setTeamTwo(Team teamTwo) {
    this.teamTwo = teamTwo;
  }
}
