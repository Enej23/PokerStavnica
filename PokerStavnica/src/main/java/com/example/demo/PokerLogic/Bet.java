package com.example.demo.PokerLogic;

public class Bet {
    float bet;
    float quota;
    int playerIndex;

    public Bet(float bet, float quota, int playerIndex) {
        this.bet = bet;
        this.quota = quota;
        this.playerIndex = playerIndex;
    }

    public float getBet() {
        return bet;
    }

    public void setBet(float bet) {
        this.bet = bet;
    }

    public float getQuota() {
        return quota;
    }

    public void setQuota(float quota) {
        this.quota = quota;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}
