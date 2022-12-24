package com.example.hw1_lanesgame;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.View;

import java.util.Random;

public class GameManager
{


    final int ROWS = 5;
    final int COLS = 5;
    final int Lifes = 3;
    private int wrong = 0;
    private int life;
    public int carPlace;
    public int[][] activeRocks = new int[ROWS][COLS];

    public GameManager() {
        this.life = Lifes;
    }

    public void setCarPlace(int place) {
        this.carPlace = place;
    }
    public int getCarPlace(){
        return this.carPlace;
    }

    public void setActiveRocks(int row, int col, int num){
        this.activeRocks[row][col] = num;
    }


    public int getWrong() {
        return wrong;
    }

    public void setPlace(boolean answer, int place) {
        if(place != 0 && place != 4){
            if(answer == true)
                setCarPlace(place-1);
            else setCarPlace(place+1);
        }
        else if(place == 0 && answer == false){
            setCarPlace(1);
        }
        else if(place == 4 && answer == true){
            setCarPlace(3);
        }
        else setCarPlace(place);
    }

    public int checkAccident(int rockPlace) {
        if(carPlace == rockPlace) {
            wrong++;
            return 1;
        }
        return 0;
    }

    public int checkCoins(int coinPlace) {
        if(carPlace == coinPlace) {
            return 10;
        }
        return 0;
    }


    public void getNewRock(){
        setActiveRocks(0, new Random().nextInt(COLS), 1);
    }

    public void getNewCoin(){
        setActiveRocks(0, new Random().nextInt(COLS), 2);
    }


    public boolean isLose() {
        return wrong == life;
    }

}
