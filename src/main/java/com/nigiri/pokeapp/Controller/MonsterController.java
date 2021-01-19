package com.nigiri.pokeapp.Controller;

import com.google.gson.Gson;
import com.nigiri.pokeapp.Models.Monster;
import com.nigiri.pokeapp.Services.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonsterController {
    @Autowired
    private MonsterService monsterService;

    @GetMapping("/Guayota")
    public ResponseEntity<Monster> getDoggo() {
    return ResponseEntity.ok(monsterService.findByID(59));
    }

    @GetMapping("/monster/{ID}")
    public ResponseEntity<Monster> getByID(@PathVariable("ID") int ID){
        return ResponseEntity.ok(monsterService.findByID(ID));
    }

}
