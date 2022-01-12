package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class HydroCylActivity extends Activity implements View.OnClickListener {
    Button btnCalc;
    EditText editPisD;
    EditText editShfD;
    EditText editPisS;
    EditText editPres;
    EditText editRate;
    TextView textPushF;
    TextView textPullF;
    TextView textPushT;
    TextView textPullT;
    TextView textPow;
    String pushF;
    String decPushF;
    String pullF;
    String decPullF;
    String pushT;
    String decPushT;
    String pullT;
    String decPullT;
    String pow;
    String decPow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydrocyl);
        setTitle("Расчёт гидроцилиндра");
        btnCalc = findViewById(R.id.btnCalc);
        editPisD = findViewById(R.id.editPisD);
        editShfD = findViewById(R.id.editShfD);
        editPisS = findViewById(R.id.editPisS);
        editPres = findViewById(R.id.editPres);
        editRate = findViewById(R.id.editRate);
        textPushF = findViewById(R.id.textResultPushF);
        textPullF = findViewById(R.id.textResultPullF);
        textPushT = findViewById(R.id.textResultPushT);
        textPullT = findViewById(R.id.textResultPullT);
        textPow = findViewById(R.id.textResultPow);
        btnCalc.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence("savedPushF", textPushF.getText());
        savedInstanceState.putCharSequence("savedPullF", textPullF.getText());
        savedInstanceState.putCharSequence("savedPushT", textPushT.getText());
        savedInstanceState.putCharSequence("savedPullT", textPullT.getText());
        savedInstanceState.putCharSequence("savedPow", textPow.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle("Расчёт гидроцилиндра");
        pushF = savedInstanceState.getString("savedPushF");
        pullF = savedInstanceState.getString("savedPullF");
        pushT = savedInstanceState.getString("savedPushT");
        pullT = savedInstanceState.getString("savedPullT");
        pow = savedInstanceState.getString("savedPow");
        textPushF.setText(pushF);
        textPullF.setText(pullF);
        textPushT.setText(pushT);
        textPullT.setText(pullT);
        textPow.setText(pow);
    }


    @Override
    public void onClick(View view) {

        CharSequence inpPisD =  "0" + editPisD.getText();
        CharSequence inpShfD =  "0" + editShfD.getText();
        CharSequence inpPisS =  "0" + editPisS.getText();
        CharSequence inpPres =  "0" + editPres.getText();
        CharSequence inpRate =  "0" + editRate.getText();

        float D = Float.parseFloat("" + inpPisD); //диаметр поршня
        float d = Float.parseFloat("" + inpShfD); //диаметр штока
        float S = Float.parseFloat("" + inpPisS); //ход поршня
        float P = Float.parseFloat("" + inpPres); //давление
        float Q = Float.parseFloat("" + inpRate); //расход

        double Fps = 0.0000785398 * D * D * P; //толкающее усилие
        double Fpl = 0.0000785398 * (D * D - d * d) * P; //тянущее усилие
        double Tps = 0.00004712388 * D * D * S / Q; //время втягивания
        double Tpl = 0.00004712388 * (D * D - d * d) * S / Q; //время выдвижения
        double Pw = P * Q / 600;
        decPushF = new DecimalFormat("0.00").format(Fps);
        decPullF = new DecimalFormat("0.00").format(Fpl);
        decPushT = new DecimalFormat("0.00").format(Tps);
        decPullT = new DecimalFormat("0.00").format(Tpl);
        decPow = new DecimalFormat("0.00").format(Pw);
        pushF = "Толкающее усилие\n" + decPushF + " кН";
        pullF = "Тянущее усилие\n" + decPullF + " кН";
        pushT = "Время выдвижения\n" + decPushT + " с";
        pullT = "Времея втягивания\n" + decPullT + " с";
        pow = "Мощность\n" + decPow + " кВт";
        textPushF.setText(pushF);
        textPullF.setText(pullF);
        textPushT.setText(pushT);
        textPullT.setText(pullT);
        textPow.setText(pow);

    }
}
