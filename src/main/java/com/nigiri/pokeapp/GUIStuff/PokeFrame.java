package com.nigiri.pokeapp.GUIStuff;

import com.nigiri.pokeapp.BattleStuff.Battle;
import com.nigiri.pokeapp.MonsterStuff.Monster;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PokeFrame extends JFrame {
    PokePanel mainPoke;
    PokePanel oppPoke;
    Monster main;
    Monster opp;
    MovesPanel movesPanel;
    public PokeFrame(Battle battle) throws IOException {
        main =battle.getTeamOne().getTeamList().get(0);
        mainPoke = new PokePanel(main);

        mainPoke.createPokeImage("src/main/resources/pokemon/main-sprites/yellow/"+ main.getId()+".png");
        opp = battle.getTeamTwo().getTeamList().get(0);
        oppPoke = new PokePanel(opp);
        oppPoke.createPokeImage("src/main/resources/pokemon/main-sprites/yellow/"+ opp.getId()+".png");
        JPanel topPanel = new JPanel();
        topPanel.add(mainPoke, Component.LEFT_ALIGNMENT);
        topPanel.add(oppPoke, Component.RIGHT_ALIGNMENT);
        add(topPanel);
        setSize(new Dimension(625, 350));
        setVisible(true);
    }

}
