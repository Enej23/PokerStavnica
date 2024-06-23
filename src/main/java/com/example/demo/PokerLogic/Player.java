package com.example.demo.PokerLogic;

import java.util.Arrays;
import java.util.List;

public class Player {

    float oddsToWin;

    float oddsToTie;

    float quotaToWin;

    public float getQuotaToWin() {
        return quotaToWin;
    }

    public void setQuotaToWin(float quotaToWin) {
        this.quotaToWin = quotaToWin;
    }

    public float getOddsToWin() {
        return oddsToWin;
    }

    public void setOddsToWin(float oddsToWin) {
        this.oddsToWin = oddsToWin; // Set the odds to win for the player
        // Calculate the quota to win and round to two decimal places
        if(oddsToWin == 0){
            setQuotaToWin(0);
            return;
        } else if (oddsToWin == 100) {
            setQuotaToWin(12345);
            return;
        }
        float quotaToWin = (float) ((1.0 / oddsToWin) * 100.0);
        // Round to two decimal places
        quotaToWin = (float) (Math.round(quotaToWin * 100.0) / 100.0);
        setQuotaToWin(quotaToWin); // Set the quota to win for the player
    }

    public float getOddsToTie() {
        return oddsToTie;
    }

    public void setOddsToTie(float oddsToTie) {
        this.oddsToTie = oddsToTie;
    }

    String card1;
    String card2;

    public Player(String card1, String card2) {
        this.card1 = card1;
        this.card2 = card2;
    }



    public String getCard1() {
        return card1;
    }

    public String getCard2() {
        return card2;
    }

    public String getBothCards(){
        return card1+card2;
    }

    public String getCard1AsPNG(){
        return card1 + ".png";
    }
    public String getCard2AsPNG(){
        return card2 + ".png";
    }
}
