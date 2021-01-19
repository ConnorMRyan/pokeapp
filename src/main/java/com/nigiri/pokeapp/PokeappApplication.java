package com.nigiri.pokeapp;

import com.google.gson.Gson;
import com.nigiri.pokeapp.Models.Team;
import com.nigiri.pokeapp.Services.MonsterService;
import com.nigiri.pokeapp.TeamStuff.TeamBuilder;
import com.nigiri.pokeapp.TeamStuff.TeamParser;
import com.nigiri.pokeapp.Utils.Launcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class PokeappApplication {

    public static void main(String[] args) throws IOException {
     SpringApplication.run(PokeappApplication.class, args);

    }

}
