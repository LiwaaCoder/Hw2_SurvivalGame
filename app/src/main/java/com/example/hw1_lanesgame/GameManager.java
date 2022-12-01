package com.example.hw1_lanesgame;

import java.util.Random;

public class GameManager
{
    static final int ROWS = 5;
    static final int COLS = 3;
    public final int lives = 3;
    public int car_location;
    public int[][] visible_stones = new int[ROWS][COLS];
    private int life_curr;
    private int wrong = 0;


    public GameManager() {
        life_curr = lives;
    }

    public int getCar_location(){
        return car_location;
    }

    public void setCar_location(int place) {
        car_location = place;
    }

    public void setActiveRocks(int row, int col, int num){
        visible_stones[row][col] = num;
    }


    public int getWrong() {
        return wrong;
    }

    public int setCar(boolean answer, int index)
    {
        if(index == 1)
        {
            if(answer == true)
                setCar_location(0);
            else setCar_location(2);
        }
        else if((index == 0 && answer == false) || (index == 2 && answer == true)){
            setCar_location(1);
        }
        else
            setCar_location(index);
        return getCar_location();
    }

    public int IsAccident(int rockPlace)
    {
        if(car_location == rockPlace) {
            wrong++;
            return 1;
        }
        return 0;
    }


    public void getStone(){
        setActiveRocks(0, new Random().nextInt(COLS), 1);
    }


    public boolean IsLost() {
        return wrong == life_curr;
    }

}