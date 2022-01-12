package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class PipeStrActivity extends Activity implements View.OnClickListener {
    Button btnCalc;
    EditText editDia;
    EditText editThs;
    EditText editLngth;
    EditText editFrc;
    TextView textMassS;
    TextView textMassA;
    TextView textStress;
    String sPipeWei;
    String aPipeWei;
    String pipeStress;
    String answSM;
    String answAM;
    //String answSigmaeq1;
    //String answSigmaeq2;
    //String answSigmaeq3;
    String answSigmaeq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipe_str);
        setTitle("Расчёт трубы на изгиб");
        btnCalc = findViewById(R.id.btnCalc);
        editDia = findViewById(R.id.editDia);
        editThs = findViewById(R.id.editThickness);
        editLngth = findViewById(R.id.editLength);
        editFrc = findViewById(R.id.editForce);
        textMassS = findViewById(R.id.textResultMassS);
        textMassA = findViewById(R.id.textResultMassA);
        textStress = findViewById(R.id.textResultStress);
        btnCalc.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence("savedMassS", textMassS.getText());
        savedInstanceState.putCharSequence("savedMassA", textMassA.getText());
        savedInstanceState.putCharSequence("savedStress", textStress.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle("Расчёт трубы на изгиб");
        sPipeWei = savedInstanceState.getString("savedStress");
        aPipeWei = savedInstanceState.getString("savedStress");
        pipeStress = savedInstanceState.getString("savedStress");
        textMassS.setText(sPipeWei);
        textMassA.setText(aPipeWei);
        textStress.setText(pipeStress);
    }



    @Override
    public void onClick(View view) {

    CharSequence inpD =  "0" + editDia.getText();
    CharSequence inps =  "0" + editThs.getText();
    CharSequence inpL =  "0" + editLngth.getText();
    CharSequence inpP =  "0" + editFrc.getText();

        float D = Float.parseFloat("" + inpD);
        float s = Float.parseFloat("" + inps);
        float L = Float.parseFloat("" + inpL);
        float P = Float.parseFloat("" + inpP);
        float d = D - 2 * s;


        double F = 0.00000025 * 3.1415928979 * (D * D - d * d);
        double sM = 0.001 * F * L * 7850;
        double aM = 0.001 * F * L * 2640;
        double taumax = 0.0015 * P / F; //МПа
        double M = P * L; //кНм
        double W = 0.00003125 * 3.1415928979 * (D * D * D * D - d * d * d * d) / D; //см3
        double sigma = M / W;
        // double sigmaeq1 = 0.5 * (sigma + Math.sqrt(sigma * sigma + 4 * taumax * taumax));
        // double sigmaeq2 = 0.35 * sigma + 0.65 * Math.sqrt(sigma * sigma + 4 * taumax * taumax);
        // double sigmaeq3 = Math.sqrt(sigma * sigma + 4 * taumax * taumax);
        double sigmaeq = Math.sqrt(sigma * sigma + 3 * taumax * taumax);
        answSM = new DecimalFormat("0.00").format(sM);
        answAM = new DecimalFormat("0.00").format(aM);
        //answSigmaeq1 = new DecimalFormat("0.00").format(sigmaeq1);
        //answSigmaeq2 = new DecimalFormat("0.00").format(sigmaeq2);
        // answSigmaeq3 = new DecimalFormat("0.00").format(sigmaeq3);
        answSigmaeq = new DecimalFormat("0.00").format(sigmaeq);



        sPipeWei = "Масса стальной трубы\n" + answSM + " кг";
        aPipeWei = "Масса трубы из АМг6\n" + answAM + " кг";
        // pipeStress = "Приведённые напряжения\n" + answSigmaeq1 + " МПа\n" + answSigmaeq2 + " МПа\n" + answSigmaeq3 + " МПа";
        pipeStress = "Приведённые напряжения\n" + answSigmaeq + " МПа";
        textMassS.setText(sPipeWei);
        textMassA.setText(aPipeWei);
        textStress.setText(pipeStress);







    }
}
