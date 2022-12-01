package com.example.hw1_lanesgame;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity
{

    final Handler handler = new Handler();
    final int DELAY = 1000;
    private final int duration = Toast.LENGTH_SHORT;
    GameManager gameManager;
    private int index = 0;
    private MaterialButton main_BTN_left;
    private MaterialButton main_BTN_right;
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_IMG_cars;
    private ShapeableImageView[][] main_IMG_rocks;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        gameManager = new GameManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }


    private void refreshUI()
    {
        if (gameManager.IsLost())
        {
            stopTimer();
            showToast("Game Over");
            finish();
        }
        else
        {
            for (int i = 0; i < gameManager.getWrong(); i++) {
                main_IMG_hearts[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void showToast(String string)
    {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, string, duration);
        toast.show();
    }

    private void findViews() {
        main_BTN_left = findViewById(R.id.game_BTN_left);
        main_BTN_right = findViewById(R.id.game_BTN_right);
        HeartsView();
        setCarsView();
        StonesView();
    }

    private void HeartsView()
    {
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3),
        };
    }

    private void setCarsView(){
        main_IMG_cars = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_car1),
                findViewById(R.id.game_IMG_car2),
                findViewById(R.id.game_IMG_car3),
        };
        main_IMG_cars[0].setVisibility(View.INVISIBLE);
        main_IMG_cars[2].setVisibility(View.INVISIBLE);
        gameManager.setCar_location(1);
    }

    private void StonesView()
    {

        main_IMG_rocks= new ShapeableImageView[][]
                {
                {findViewById(R.id.game_IMG_stoneC1),
                        findViewById(R.id.game_IMG_stoneC2),
                        findViewById(R.id.game_IMG_stoneC3)},

                {findViewById(R.id.game_IMG_stoneC4),
                        findViewById(R.id.game_IMG_stoneC5),
                        findViewById(R.id.game_IMG_stoneC6)},


                {findViewById(R.id.game_IMG_stoneC7),
                        findViewById(R.id.game_IMG_stoneC8),
                        findViewById(R.id.game_IMG_stoneC9)},


                {findViewById(R.id.game_IMG_stoneC10),
                        findViewById(R.id.game_IMG_stoneC11),
                        findViewById(R.id.game_IMG_stoneC12)},


                {findViewById(R.id.game_IMG_stoneC13),
                        findViewById(R.id.game_IMG_stoneC14),
                        findViewById(R.id.game_IMG_stoneC15)}

        };
        //gameManager.getStone();
        update();
    }

    private void initViews()
    {
        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(true);
            }
        });

        main_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked(false);
            }
        });
        startTimer();
    }
    private void clicked(boolean answer)
    {
        gameManager.setCar(answer, gameManager.getCar_location());
        main_IMG_cars[gameManager.getCar_location()].setVisibility(View.VISIBLE);
        for (int i = 0; i < main_IMG_cars.length; i++)
        {
            if(i != gameManager.getCar_location())
                main_IMG_cars[i].setVisibility(View.INVISIBLE);
        }
    }

    private void startTimer() {
        handler.postDelayed(runnable, DELAY);
    }
    private void stopTimer() {
        handler.removeCallbacks(runnable);
    }Runnable runnable = new Runnable() {
        public void run() {
            handler.postDelayed(this, DELAY);
            updateRocksView();
        }
    };

    private void vibrate()
    {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    private void updateRocksView()
    {
        for (int i = 0; i < gameManager.COLS; i++){
            for(int j = 0; j < gameManager.ROWS; j++){
                if (gameManager.visible_stones[j][i] == 1)
                {
                    if(j != gameManager.ROWS-1){
                        gameManager.setActiveRocks(j, i, 0);
                        gameManager.setActiveRocks(j+1, i, 1);
                        if (i != gameManager.COLS-1)
                            i++;
                    }
                    else {
                        gameManager.setActiveRocks(j, i, 0);
                        if (gameManager.IsAccident(i) == 1)
                        {
                            vibrate();
                            showToast("You lost your " + gameManager.getWrong() + " life");
                        }
                        refreshUI();
                        if(gameManager.getWrong() != gameManager.lives)
                            gameManager.getStone();
                    }
                }
            }
        }
        index++;
        if(index == 3)
        {
            gameManager.getStone();
            index = 0;
        }
        update();
    }

    private void update()
    {
        for (int i = 0; i < gameManager.ROWS; i++) {
            for (int j = 0; j < gameManager.COLS; j++) {
                if(gameManager.visible_stones[i][j] == 0)
                    main_IMG_rocks[i][j].setVisibility(View.INVISIBLE);
                else
                    main_IMG_rocks[i][j].setVisibility(View.VISIBLE);
            }
        }
    }





}