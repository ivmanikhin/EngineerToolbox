package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class RPipeStrActivity extends Activity implements View.OnClickListener {
    Button btnCalc;
    EditText editHei;
    EditText editBre;
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
        setContentView(R.layout.activity_rpipe_str);
        setTitle("Расчёт прямоугольной трубы на изгиб");
        btnCalc = findViewById(R.id.btnCalc);
        editHei = findViewById(R.id.editHei);
        editBre = findViewById(R.id.editBre);
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
        setTitle("Расчёт прямоугольной трубы на изгиб");
        sPipeWei = savedInstanceState.getString("savedStress");
        aPipeWei = savedInstanceState.getString("savedStress");
        pipeStress = savedInstanceState.getString("savedStress");
        textMassS.setText(sPipeWei);
        textMassA.setText(aPipeWei);
        textStress.setText(pipeStress);
    }

    @Override
    public void onClick(View view) {

        CharSequence inpH =  "0" + editHei.getText();
        CharSequence inpB =  "0" + editBre.getText();
        CharSequence inps =  "0" + editThs.getText();
        CharSequence inpL =  "0" + editLngth.getText();
        CharSequence inpP =  "0" + editFrc.getText();

        float H = Float.parseFloat("" + inpH);
        float B = Float.parseFloat("" + inpB);
        float s = Float.parseFloat("" + inps);
        float L = Float.parseFloat("" + inpL);
        float P = Float.parseFloat("" + inpP);


        double F = 0.000001 * (B * H - (B - 2 * s) * (H - 2 * s));
        double sM = 0.001 * F * L * 7850; //масса стальной трубы
        double aM = 0.001 * F * L * 2640; //масса амг трубы
        double taumax = 0.0015 * P / F; //МПа
        double M = P * L; //кНм
        double W = 0.001 * (s * H * H / 3) * (3 * B / H + 1) ; //см3
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
