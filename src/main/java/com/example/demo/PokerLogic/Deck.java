package com.example.demo.PokerLogic;

import java.lang.annotation.Retention;
import java.util.*;

public class Deck {

    private LinkedList<String> cards;



    public Deck() {
        this.cards = new LinkedList<>();
        initializeDeck();
        Collections.shuffle(cards);

    }

    private void initializeDeck() {
        String[] suits = {"h", "d", "c", "s"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                this.cards.add(rank + suit);
            }
        }
    }

    // Methods to shuffle, deal, etc. can be added here

    public LinkedList<String> getCards() {
        return cards;
    }
    public String giveCard(){
       String card = this.cards.get(0);
       this.cards.remove(0);
       return card;
    }

    public String giveFlop(){
        String flop = "";
        for(int i = 0; i < 3;i++){
            flop += giveCard();
        }
        return flop;
    }

}