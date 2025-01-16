package com.adaptionsoft.games.uglytrivia;

public class Player {
    
    private String name;
    private int position = 0;
    private int nbCoins = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNbCoins() {
        return nbCoins;
    }

    public void addCoin() {
        nbCoins++;
    }

    @Override
    public String toString() {
        return name;
    }
}
