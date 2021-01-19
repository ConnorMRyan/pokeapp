package com.nigiri.pokeapp.Controller;

import com.google.gson.Gson;
import com.nigiri.pokeapp.Models.Team;
import com.nigiri.pokeapp.TeamStuff.TeamParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class TeamController {

    @GetMapping("/team/{teamName}")
    public ResponseEntity<Team> getTeam(@PathVariable String teamName){
        try{
            Team team = TeamParser.makeTeam(new File("src/main/resources/teams/" + teamName +".txt"));
            team.setActiveMonster();
            return ResponseEntity.ok(team);
        }catch (IOException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}

