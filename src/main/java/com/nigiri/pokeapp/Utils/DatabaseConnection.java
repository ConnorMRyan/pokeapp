package com.nigiri.pokeapp.Utils;

import com.nigiri.pokeapp.Models.Monster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    Connection db;

    public DatabaseConnection() {
        try {
            this.db =
                    DriverManager.getConnection(
                            "jdbc:postgresql://localhost/pokeMaven", "postgres", "password");
        } catch (Exception e) {
            System.err.println("Could not connect!");
        }
    }

    public Monster makeAMonster(int pokemonID, int level, String name) {
        String query = "SELECT * FROM pokemon where number = " + pokemonID;
        return getMonster(level, query, name);
    }

    public Monster makeAMonster(int pokemonID, int level) {
        String query = "SELECT * FROM pokemon where number = " + pokemonID;
        return getMonster(level, query);
    }

    public Monster makeAMonster(String pokemonName, int level, String name) {
        String query = "SELECT * FROM pokemon WHERE pokemon.name = " + "'" + pokemonName + "'";
        return getMonster(level, query, name);
    }

    public Monster makeAMonster(String pokemonName, int level) {
        String query = "SELECT * FROM pokemon WHERE pokemon.name = " + "'" + pokemonName + "'";
        return getMonster(level, query);
    }

    private Monster getMonster(int level, String query, String name) {
        try {
            Statement stmt = db.createStatement();
            ResultSet results = stmt.executeQuery(query);
            results.next();
            return new Monster(
                    results.getInt("HP"),
                    results.getInt("Attack"),
                    results.getInt("Defense"),
                    results.getInt("Special"),
                    results.getInt("Speed"),
                    results.getInt("typeOne"),
                    results.getInt("typeTwo"),
                    level,
                    name,
                    results.getString("name"), results.getInt("ID"));
        } catch (Exception e) {
            System.err.println("Couldn't create pokemon");
            return new Monster(0, 0, 0, 0, 0, 0, 0, 0, "missingNo", 0);
        }
    }

    private Monster getMonster(int level, String query) {
        try {
            Statement stmt = db.createStatement();
            ResultSet results = stmt.executeQuery(query);
            results.next();
            return new Monster(
                    results.getInt("HP"),
                    results.getInt("Attack"),
                    results.getInt("Defense"),
                    results.getInt("Special"),
                    results.getInt("Speed"),
                    results.getInt("typeOne"),
                    results.getInt("typeTwo"),
                    level,
                    results.getString("name"), results.getInt("ID"));
        } catch (Exception e) {
            System.err.println("Couldn't create pokemon");
            return new Monster(0, 0, 0, 0, 0, 0, 0, 0, "missingNo", 0);
        }
    }

    public boolean isValidPoke(String pokename) {
        String query = "SELECT * from pokemon where pokemon.name = '" + pokename + "'";
        try {
            Statement stmt = db.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();
            return resultSet.getString("name") != null;
        } catch (Exception e) {
            System.err.println("Sorry, that is not a valid pokemon name.");
            return false;
        }
    }
}
