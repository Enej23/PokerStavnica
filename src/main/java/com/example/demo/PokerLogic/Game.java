package com.example.demo.PokerLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    List<Bet> bets = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    Deck deck;
    String flop;
    String river;
    String turn;

    float lastGamePayout;

    float sumOfGameBets;
    int indexOfGameWinner;

    public Game(int numOfPlayers){
        this.deck = new Deck();
        this.flop = "";
        this.river = "";
        this.turn = "";
        for(int i = 0; i < numOfPlayers;i++){
            players.add(new Player(deck.giveCard(), deck.giveCard()));
        }
        this.sumOfGameBets = 0;
        this.lastGamePayout = 0;
    }
    public String getBoard(){
        return flop + turn + river;
    }

    public void addBet(float bet, float quota, int playerIndex){
        this.bets.add(new Bet(bet,quota,playerIndex));
    }
    public void addFlop(){
        this.flop = this.deck.giveFlop();
    }

    public void addTurn(){
        this.turn = this.deck.giveCard();
    }
    public void addRiver(){
        this.river = this.deck.giveCard();
    }

    public String getPlayerHands(){
        String playerHands = "";
        for(Player player : players){
            playerHands += player.getBothCards();
            playerHands += ",";
        }
        playerHands = playerHands.substring(0,playerHands.length()-1);


        return playerHands;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getIndexOfGameWinner() {
        return indexOfGameWinner;
    }

    public void setIndexOfGameWinner(int indexOfGameWinner) {
        this.indexOfGameWinner = indexOfGameWinner;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public float getLastGamePayout() {
        return lastGamePayout;
    }

    public void setLastGamePayout(float lastGamePayout) {
        this.lastGamePayout = lastGamePayout;
    }

    public float getSumOfGameBets() {
        return sumOfGameBets;
    }

    public void setSumOfGameBets(float sumOfGameBets) {
        this.sumOfGameBets = sumOfGameBets;
    }
}
