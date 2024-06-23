package com.example.demo;

import com.example.demo.PokerLogic.Game;
import com.example.demo.PokerLogic.Player;
import com.example.demo.PokerLogic.PokerOddsCalc;
import org.springframework.boot.SpringApplication;

public class Testing {

    public static void main(String[] args) {

		PokerOddsCalc pokerOddsCalc = new PokerOddsCalc();
		Game game = new Game(6);
		pokerOddsCalc.setWinningOddsBeforeFlop(game);
		System.out.println("before flop");
		for(Player player : game.getPlayers()){
			System.out.println(player.getQuotaToWin());

		}
		game.addFlop();
		System.out.println("after flop");
		pokerOddsCalc.setWinningOddsAfterFlop(game);
		for(Player player : game.getPlayers()){
			System.out.println(player.getQuotaToWin());
		}
		System.out.println(game.getPlayerHands());
		System.out.println(game.getBoard());
		game.addTurn();
		System.out.println(game.getBoard());
        pokerOddsCalc.setWinningOddsAfterFlop(game);
        for(Player player : game.getPlayers()){
            System.out.println(player.getQuotaToWin()+ " " + player.getOddsToWin());
        }
		game.addRiver();
		System.out.println(game.getBoard());
        pokerOddsCalc.setWinningOddsAfterFlop(game);
        for(Player player : game.getPlayers()){
            System.out.println(player.getBothCards()+ " "+ player.getQuotaToWin()+ " " + player.getOddsToWin());
        }

    }

}
