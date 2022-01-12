package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Deck Machinery Tools");

        Button btnPipeStrngth = findViewById(R.id.pipe_strength);
        Button btnRPipeStrngth = findViewById(R.id.rpipe_strength);
        Button btnEyeStrngth = findViewById(R.id.eye_strength);
        Button btnShaft_strength = findViewById(R.id.shaft_strength);
        Button btnHydroCyl = findViewById(R.id.hydrocyl);
        Button btnWCooler = findViewById(R.id.wcooler);
        Button btnWinch = findViewById(R.id.winch);
        btnPipeStrngth.setOnClickListener(this);
        btnRPipeStrngth.setOnClickListener(this);
        btnEyeStrngth.setOnClickListener(this);
        btnShaft_strength.setOnClickListener(this);
        btnHydroCyl.setOnClickListener(this);
        btnWCooler.setOnClickListener(this);
        btnWinch.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.pipe_strength:
                i = new Intent(this, PipeStrActivity.class);
                startActivity(i);
                break;
            case R.id.rpipe_strength:
                i = new Intent(this, RPipeStrActivity.class);
                startActivity(i);
                break;
            case R.id.eye_strength:
                i = new Intent(this, EyeStrActivity.class);
                startActivity(i);
                break;
            case R.id.shaft_strength:
                i = new Intent(this, ShaftStrActivity.class);
                startActivity(i);
                break;
            case R.id.hydrocyl:
                i = new Intent(this, HydroCylActivity.class);
                startActivity(i);
                break;
            case R.id.wcooler:
                i = new Intent(this, WCoolerActivity.class);
                startActivity(i);
                break;
            case R.id.winch:
                i = new Intent(this, WinchActivity.class);
                startActivity(i);
                break;

        }

    }
}

