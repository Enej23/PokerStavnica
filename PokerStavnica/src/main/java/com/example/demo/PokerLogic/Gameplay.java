package com.example.demo.PokerLogic;

public class Gameplay {
    float balance;
    float casinoBalance;






    public Gameplay(float balance, float casinoBalance) {
        this.balance = balance;
        this.casinoBalance = casinoBalance;
    }


    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getCasinoBalance() {
        return casinoBalance;
    }

    public void setCasinoBalance(float casinoBalance) {
        this.casinoBalance = casinoBalance;
    }
}
