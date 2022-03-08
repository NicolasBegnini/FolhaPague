package com.example.folhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView t_salarioB, t_irrf, t_salarioL, t_PS, t_inss, vale_t, vale_r, vale_a;
    private Button btFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        t_salarioB = findViewById(R.id.textv_SB);
        t_irrf = findViewById(R.id.textv_IRRF);
        t_salarioL = findViewById(R.id.textv_SL);
        t_PS = findViewById(R.id.textv_PS);
        t_inss = findViewById(R.id.textv_INSS);
        vale_a = findViewById(R.id.t_vA);
        vale_r = findViewById(R.id.t_vR);
        vale_t = findViewById(R.id.t_vT);
        btFinal = findViewById(R.id.btfinal);





        Bundle resul = getIntent().getExtras();
        t_salarioB.setText(resul.getString("salarioBruto"));
        t_inss.setText(resul.getString("inss"));
        t_salarioL.setText(resul.getString("salarioLiquido"));
        t_irrf.setText(resul.getString("irrf"));
        t_PS.setText(resul.getString("planoSaude"));
        vale_r.setText(resul.getString("vale_r"));
        vale_a.setText(resul.getString("vale_a"));
        vale_t.setText(resul.getString("vale_t"));



    }
    public void finalizar(View view){


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}