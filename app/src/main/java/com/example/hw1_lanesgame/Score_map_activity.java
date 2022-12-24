package com.example.hw1_lanesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.button.MaterialButton;

public class Score_map_activity extends AppCompatActivity
{

    private FragmentList fragmentList;
    private fragment_map fragmentMap;
    private FrameLayout score_LAY_list;
    private FrameLayout score_LAY_map;
    private MaterialButton score_BTN_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_map);

        fragmentList = new FragmentList();
        fragmentMap = new fragment_map();

        findViews();
        initViews();

        getSupportFragmentManager().beginTransaction().add(R.id.score_LAY_list, fragmentList).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.score_LAY_map, fragmentMap).commit();
    }

    private void findViews() {
        score_LAY_list = findViewById(R.id.score_LAY_list);
        score_LAY_map = findViewById(R.id.score_LAY_map);
        score_BTN_button = findViewById(R.id.score_BTN_button);
    }

    private void initViews() {
        score_BTN_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartPage();
            }
        });
    }

    private void openStartPage() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}