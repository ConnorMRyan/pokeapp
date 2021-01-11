package com.nigiri.pokeapp.Utils;

import com.nigiri.pokeapp.MonsterStuff.Monster;
import com.nigiri.pokeapp.MonsterStuff.MonsterDAO;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.Move;

public class Scripts {
  public static void main(String[] args) throws Exception {
    PokeApi pokeApi = new PokeApiClient();
    Move move = pokeApi.getMove(1);
    System.out.println(move);
  }
}
