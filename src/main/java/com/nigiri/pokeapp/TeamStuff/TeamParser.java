package com.nigiri.pokeapp.TeamStuff;

import com.nigiri.pokeapp.ActionStuff.BattleMove;
import com.nigiri.pokeapp.ActionStuff.StatusBoostMove;
import com.nigiri.pokeapp.Models.DAO.MonsterDAO;
import com.nigiri.pokeapp.Models.Monster;
import com.nigiri.pokeapp.Models.Team;
import com.nigiri.pokeapp.Utils.MonsterBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Takes in and creates a new team from a File

public class TeamParser {
  public static Team makeTeam(File file) throws FileNotFoundException {
    Scanner in = new Scanner(file);
    // DatabaseConnection db = new DatabaseConnection();
    String teamName = in.nextLine();
    Team team = new Team(teamName);
    int numPokemon = in.nextInt();
    in.nextLine();
    for (int i = 0; i < numPokemon; i++) {
      int numMoves = in.nextInt();
      in.nextLine();
      String pokeString = in.nextLine();
      Scanner pokeScanner = new Scanner(pokeString);
      pokeScanner.useDelimiter(",");
      int id = pokeScanner.nextInt();
      int level = pokeScanner.nextInt();
      String nick = pokeScanner.next();
      if (!nick.equals("null")) {
        Monster monster = new Monster(MonsterBuilder.buildByID(id), level, nick);
        getMove(in, team, numMoves, monster);
      } else {
        MonsterDAO monsterDAO = MonsterBuilder.buildByID(id);
        Monster monster = new Monster(monsterDAO, level, monsterDAO.getFAMILY_NAME());
        getMove(in, team, numMoves, monster);
      }
      pokeScanner.close();
    }
    in.close();
    return team;
  }

  private static void getMove(Scanner in, Team team, int numMoves, Monster monster) {
    for (int j = 0; j < numMoves; j++) {
      int moveType = in.nextInt();
      in.nextLine();
      String moveString = in.nextLine();
      if (moveType == 0) {
        monster.addMove(new BattleMove(moveString));
      }
      if (moveType == 2) {
        monster.addMove(new StatusBoostMove(moveString));
      }
    }

    team.addMonster(monster);
  }

  static void saveTeam(Team team) {}
}
