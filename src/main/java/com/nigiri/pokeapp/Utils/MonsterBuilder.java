package com.nigiri.pokeapp.Utils;

import com.nigiri.pokeapp.Models.DAO.MonsterDAO;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonStat;

import java.util.ArrayList;

public class MonsterBuilder {
  public static MonsterDAO buildByID(int ID) {
    PokeApi pokeApi = new PokeApiClient();
    Pokemon pokemon = pokeApi.getPokemon(ID);
    return new MonsterDAO(
        pokemon.getId(),
        pokemon.getName(),
        types.getType(pokemon.getTypes().get(0).getType().getName()),
            pokemon.getTypes().size() == 2 ? types.getType(pokemon.getTypes().get(1).getType().getName()) : types.NO_TYPE,
            (ArrayList<PokemonStat>) pokemon.getStats());
  }
}
