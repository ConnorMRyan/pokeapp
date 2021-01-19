package com.nigiri.pokeapp.Services;

import com.nigiri.pokeapp.Models.Monster;
import com.nigiri.pokeapp.Repositories.MonsterRepo;
import com.nigiri.pokeapp.Utils.MonsterBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterService implements MonsterRepo {

    @Override
    public Monster findByID(int ID) {
        //return monsterRepo.findByID(ID);
        return new Monster(MonsterBuilder.buildByID(ID),69,"Guayota");
    }

    @Override
    public List<Monster> findAll() {
        return null;
    }
}
