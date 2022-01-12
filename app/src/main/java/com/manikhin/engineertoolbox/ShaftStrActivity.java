package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;


public class ShaftStrActivity extends Activity implements View.OnClickListener {
    Button btnCalc;
    EditText editDia;
    EditText editLngth;
    EditText editFrc;
    TextView textStress;
    String shaftStress;
    String answSigmaeq;
    ImageView ShaftStressPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaft_str);
        setTitle("Расчёт оси");
        ShaftStressPic = findViewById(R.id.imageShaftStressPic);
        ShaftStressPic.setImageResource(R.drawable.shaftstresspic);
        btnCalc = findViewById(R.id.btnCalc);
        editDia = findViewById(R.id.editDia);
        editLngth = findViewById(R.id.editLength);
        editFrc = findViewById(R.id.editForce);
        textStress = findViewById(R.id.textResultStress);
        btnCalc.setOnClickListener(this);
    }


   @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence("savedShaftStress", textStress.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle("Расчёт оси");
        shaftStress = savedInstanceState.getString("savedShaftStress");
        textStress.setText(shaftStress);
    }

    @Override
    public void onClick(View view) {

        CharSequence inpD =  "0" + editDia.getText();
        CharSequence inpL =  "0" + editLngth.getText();
        CharSequence inpP =  "0" + editFrc.getText();

        float D = Float.parseFloat("" + inpD);
        float L = Float.parseFloat("" + inpL);
        float P = Float.parseFloat("" + inpP);


        double F = 0.00000025 * 3.1415928979 * D * D;
        double sM = 0.001 * F * L * 7850;
        double aM = 0.001 * F * L * 2640;
        double taumax = 0.0015 * P / F; //МПа
        double M = P * L; //кНм
        double W = 0.00003125 * 3.1415928979 * D * D * D; //см3
        double sigma = M / W;
        double sigmaeq = Math.sqrt(sigma * sigma + 3 * taumax * taumax);
        answSigmaeq = new DecimalFormat("0.00").format(sigmaeq);



        shaftStress = "Приведённые напряжения\n" + answSigmaeq + " МПа";
        textStress.setText(shaftStress);
    }
}
