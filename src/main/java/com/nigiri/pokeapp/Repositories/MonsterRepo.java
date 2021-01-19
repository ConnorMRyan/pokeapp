package com.nigiri.pokeapp.Repositories;

import com.nigiri.pokeapp.Models.Monster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRepo  {
    //Create
    //Read
    Monster findByID(int ID);
    List<Monster> findAll();
    //Update
    //Delete

}
