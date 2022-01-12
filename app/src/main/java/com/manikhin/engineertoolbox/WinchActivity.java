package com.manikhin.engineertoolbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

//import androidx.annotation.RecentlyNonNull;


public class WinchActivity extends Activity implements View.OnClickListener {
    Button btnCalc;
    EditText editWireDia;
    EditText editWireLength;
    EditText editDrumDia;
    EditText editLayNumber;
    EditText editWireSpeedFL;
    EditText editPullForceFL;
    TextView textResultFullDrumDia;
    TextView textResultDrumLength;
    TextView textResultPullForceLL;
    TextView textResultEffectiveForce;
    TextView textResultWireSpeedLL;
    TextView textResultMechPow;
    TextView textResultTorque;
    String fullDD;
    String decFullDD;
    String drumL;
    String decDrumL;
    String forceLL;
    String decForceLL;
    String effForce;
    String decEffForce;
    String speedLL;
    String decSpeedLL;
    String mechPow;
    String decMechPow;
    String torque;
    String decTorque;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winch);
        setTitle("Расчёт лебёдки");
        btnCalc = findViewById(R.id.btnCalc);
        editWireDia = findViewById(R.id.editWireDia);
        editWireLength = findViewById(R.id.editWireLength);
        editDrumDia = findViewById(R.id.editDrumDia);
        editLayNumber = findViewById(R.id.editLayNumber);
        editWireSpeedFL = findViewById(R.id.editWireSpeedFL);
        editPullForceFL = findViewById(R.id.editPullForceFL);
        textResultFullDrumDia = findViewById(R.id.textResultFullDrumDia);
        textResultDrumLength = findViewById(R.id.textResultDrumLength);
        textResultPullForceLL = findViewById(R.id.textResultPullForceLL);
        textResultEffectiveForce = findViewById(R.id.textResultEffectiveForce);
        textResultWireSpeedLL = findViewById(R.id.textResultWireSpeedLL);
        textResultMechPow = findViewById(R.id.textResultMechPow);
        textResultTorque = findViewById(R.id.textResultTorque);
        btnCalc.setOnClickListener(this);



    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putCharSequence("savedFullDD", textResultFullDrumDia.getText());
        savedInstanceState.putCharSequence("savedDrumL", textResultDrumLength.getText());
        savedInstanceState.putCharSequence("savedForceLL", textResultPullForceLL.getText());
        savedInstanceState.putCharSequence("savedSpeedLL", textResultWireSpeedLL.getText());
        savedInstanceState.putCharSequence("savedEffForce", textResultEffectiveForce.getText());
        savedInstanceState.putCharSequence("savedTorque", textResultTorque.getText());
        savedInstanceState.putCharSequence("savedMechPow", textResultMechPow.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle("Расчёт лебёдки");
        fullDD = savedInstanceState.getString("savedFullDD");
        drumL = savedInstanceState.getString("savedDrumL");
        forceLL = savedInstanceState.getString("savedForceLL");
        speedLL = savedInstanceState.getString("savedSpeedLL");
        effForce = savedInstanceState.getString("savedEffForce");
        torque = savedInstanceState.getString("savedTorque");
        mechPow = savedInstanceState.getString("savedMechPow");
        textResultFullDrumDia.setText(fullDD);
        textResultDrumLength.setText(drumL);
        textResultPullForceLL.setText(forceLL);
        textResultEffectiveForce.setText(effForce);
        textResultWireSpeedLL.setText(speedLL);
        textResultTorque.setText(torque);
        textResultMechPow.setText(mechPow);
    }




    @Override
    public void onClick(View view) {

        CharSequence inpWireD =  "0" + editWireDia.getText();
        CharSequence inpWireL =  "0" + editWireLength.getText();
        CharSequence inpDrumD =  "0" + editDrumDia.getText();
        CharSequence inpLayN =  "0" + editLayNumber.getText();
        CharSequence inpWireVfl =  "0" + editWireSpeedFL.getText();
        CharSequence inpPullFfl = "0" + editPullForceFL.getText();

        float d = Float.parseFloat("" + inpWireD); //диаметр троса
        float L = Float.parseFloat("" + inpWireL); //длина троса
        float D = Float.parseFloat("" + inpDrumD); //диаметр пустого барабана
        float n = Float.parseFloat("" + inpLayN); //кол-во слоёв
        float Vfl = Float.parseFloat("" + inpWireVfl); //скорость на 1 слое
        float Ffl = Float.parseFloat("" + inpPullFfl); //тяговое усилие на 1 слое

        double Dll = D + 2 * n * d; //диаметр барабана с тросом
        double Ld = 1273.2395 * d * d * L / (Dll * Dll - D * D); //длина барабана
        double Fll = Ffl * D / Dll; //тяговое усилие на последнем слое
        double Vll = Vfl * Dll / D; //скорость на последнем слое
        double M = 0.0005 * D * Ffl; //крутящий момент
        double Qm = Vfl * Ffl / 60; // механическая мощность
        double Feff = Ffl - L * d * d * 0.0000329039; // эффективное усилие при вытравленном в водо тросе

        decFullDD = new DecimalFormat("0.00").format(Dll);
        decDrumL = new DecimalFormat("0.00").format(Ld);
        decForceLL = new DecimalFormat("0.00").format(Fll);
        decSpeedLL = new DecimalFormat("0.00").format(Vll);
        decTorque = new DecimalFormat("0.00").format(M);
        decMechPow = new DecimalFormat("0.00").format(Qm);
        decEffForce = new DecimalFormat("0.00").format(Feff);

        fullDD = "Диаметр барабана с тросом\n" + decFullDD + " мм";
        drumL = "Длина барабана\n" + decDrumL + " мм";
        forceLL = "Тяговое усилие на верхнем слое\n" + decForceLL + " кН";
        speedLL = "Скорость на верхнем слое\n" + decSpeedLL + " м/мин";
        effForce = "Эффективное тяговое усилие на первом слое\n" + decEffForce + " кН";
        torque = "Крутящий момент\n" + decTorque + " кН*м";
        mechPow = "Механическая мощность\n" + decMechPow + " кВт";

        textResultFullDrumDia.setText(fullDD);
        textResultDrumLength.setText(drumL);
        textResultPullForceLL.setText(forceLL);
        textResultEffectiveForce.setText(effForce);
        textResultWireSpeedLL.setText(speedLL);
        textResultTorque.setText(torque);
        textResultMechPow.setText(mechPow);


    }
}
