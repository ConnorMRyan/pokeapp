package com.nigiri.pokeapp.Repositories;

import com.nigiri.pokeapp.Models.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo {

    Team getByName(String name);

}
