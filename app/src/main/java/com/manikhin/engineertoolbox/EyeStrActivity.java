package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;


public class EyeStrActivity extends Activity implements View.OnClickListener {
    Button btnCalc;
    EditText editEyeR;
    EditText editEyeD;
    EditText editEyeS;
    EditText editFrc;
    TextView textStress;
    String eyeStress;
    String answEyeStress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_str);
        setTitle("Расчёт проушины");
        btnCalc = findViewById(R.id.btnCalc);
        editEyeR = findViewById(R.id.editEyeR);
        editEyeD = findViewById(R.id.editEyeD);
        editEyeS = findViewById(R.id.editEyeS);
        editFrc = findViewById(R.id.editForce);
        textStress = findViewById(R.id.textResultStress);
        btnCalc.setOnClickListener(this);
        editEyeR.addTextChangedListener(textWatcher);
        editEyeD.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence("savedStress", textStress.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle("Расчёт проушины");
        answEyeStress = savedInstanceState.getString("savedStress");
        textStress.setText(answEyeStress);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            CharSequence inpR =  "0" + editEyeR.getText();
            CharSequence inpD =  "0" + editEyeD.getText();


            float d = Float.parseFloat("" + inpD);
            float R = Float.parseFloat("" + inpR);

            if (d >= 2 * R)
            {
                textStress.setText("Отверстие больше самого изделия");
                btnCalc.setEnabled(false);
            }
            else {
                btnCalc.setEnabled(true);
                textStress.setText("Напряжения в проушине");
            }
        }
    };


    @Override
    public void onClick(View view) {

        CharSequence inpR =  "0" + editEyeR.getText();
        CharSequence inpD =  "0" + editEyeD.getText();
        CharSequence inpS =  "0" + editEyeS.getText();
        CharSequence inpF =  "0" + editFrc.getText();

        float d = Float.parseFloat("" + inpD);
        float R = Float.parseFloat("" + inpR);
        float s = Float.parseFloat("" + inpS);
        float F = Float.parseFloat("" + inpF);

        if ((d >= 2 * R) && ((d == 0) || (R == 0) || (s == 0) || (F == 0)))
            {
            textStress.setText("Отверстие больше самого изделия, да ещё и нулевые значения. Хорошая попытка, товарищ");
            }
        else if ((d == 0) || (R == 0) || (s == 0) || (F == 0))
            {
            textStress.setText("Нулевые значения");
            }
        else
            {
            double sigma = F / (d * s) * (R * R + 0.25 * d * d) / (R * R - 0.25 * d * d) * 1000;
            eyeStress = new DecimalFormat("0.00").format(sigma);
            answEyeStress = "Напряжения в проушине\n" + eyeStress + " МПа";
            textStress.setText(answEyeStress);
            }
    }
}
