package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class WCoolerActivity extends Activity implements View.OnClickListener {
    Button btnCalc;
    EditText editInTemp;
    EditText editOutTemp;
    EditText editHeatPow;
    TextView textWRate;
    String decWRateM3perHr;
    String wRate;
    String decWRateLperMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wcooler);
        setTitle("Расчёт водяного охлаждения");
        btnCalc = findViewById(R.id.btnCalc);
        editInTemp = findViewById(R.id.editInTemp);
        editOutTemp = findViewById(R.id.editOutTemp);
        editHeatPow = findViewById(R.id.editHeatPow);
        textWRate = findViewById(R.id.textResultWRate);
        btnCalc.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence("savedWRate", textWRate.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle("Расчёт водяного охлаждения");
        wRate = savedInstanceState.getString("savedFullDD");
        textWRate.setText(wRate);
    }
    @Override
    public void onClick(View view) {

        CharSequence inpInTemp =  "0" + editInTemp.getText();
        CharSequence inpOutTemp =  "0" + editOutTemp.getText();
        CharSequence inpHeatPow =  "0" + editHeatPow.getText();

        float Tin = Float.parseFloat("" + inpInTemp); //температура воды на входе
        float Tout = Float.parseFloat("" + inpOutTemp); //температура воды на выходе
        float Q = Float.parseFloat("" + inpHeatPow); //тепловая мощность


        double Gmperh = 0.8612 * Q / (Tout - Tin); //расход воды, м3 в час
        double Glperm = Gmperh * 16.6667; //расход воды, л в мин
        decWRateM3perHr = new DecimalFormat("0.00").format(Gmperh);
        decWRateLperMin = new DecimalFormat("0.00").format(Glperm);
        wRate = "Расход воды\n" + decWRateM3perHr + " м³/ч\n" + decWRateLperMin + " л/мин";
        textWRate.setText(wRate);


    }
}
